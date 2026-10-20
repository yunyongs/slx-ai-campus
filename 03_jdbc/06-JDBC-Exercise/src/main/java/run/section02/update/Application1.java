package run.section02.update;
import model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class Application1 {


    /*
     * 1. 메뉴 변경
     * 변경할 메뉴 번호를 입력받고
     * 해당 메뉴의 변경사항을 입력받는다
     * (메뉴이름, 메뉴가격)
     * 메뉴를 변경한다.
     *
     * 2. 변경할 메뉴 번호를 입력하면 현재 메뉴 정보가 보이고 메뉴의 변경사항을 입력받는다.
     * */


    public static void main(String[] args) {

        Properties prop = new Properties();
        Connection con = getConnection();
        PreparedStatement selectPstmt = null; //메뉴 목록 조회용
        PreparedStatement updatePstmt = null; //메뉴 변경용
        ResultSet rset = null;

        List<MenuDTO> menuList = new ArrayList<>();
        int result = 0; //변경된 행의 수 조회

        try {
            // Query문 작성된 xml 읽기
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu-query.xml"));
//            System.out.println("쿼리문 = " + prop.getProperty("selectAllMenu"));

            // 1) 메뉴 리스트 보여주기
            String selectAll = prop.getProperty("selectAllMenu");
            selectPstmt = con.prepareStatement(selectAll);
            rset = selectPstmt.executeQuery();

            while (rset.next()) {
                menuList.add(new MenuDTO(
                        rset.getInt("menu_code"),
                        rset.getString("menu_name"),
                        rset.getInt("menu_price"),
                        rset.getInt("category_code"),
                        rset.getString("orderable_status")
                ));
            }
            System.out.println("=== 메뉴 목록입니다. ===");
            for (MenuDTO dto : menuList) {
                System.out.println(dto);
            }

            Scanner sc = new Scanner(System.in);
            // 2) 변경할 메뉴 번호 입력 -> 현재 메뉴 정보 보여주기
            System.out.println("=== 변경할 메뉴의 번호를 입력해 주세요 (menu_code) ===");
            int menuCode = sc.nextInt();
            sc.nextLine(); //nextInt()가 남긴 개행(\n) 비우기

            MenuDTO selectedMenu = null;
            for (MenuDTO dto : menuList) {
                if (dto.getCode() == menuCode) {
                    selectedMenu = dto;
                }
            }
            if(selectedMenu == null) {
                System.out.println("해당 번호의 메뉴가 없습니다.");
                return;
            }
            System.out.println("선택된 메뉴의 정보: " + selectedMenu);

            // 3) 변경사항 입력(이름 -> 가격, 하나씩)
            System.out.println("선택된 메뉴의 변경할 이름을 입력해주세요 : ");
            String menuName = sc.nextLine();

            System.out.println("선택된 메뉴의 변경할 가격을 입력하세요 : ");
            int menuPrice = sc.nextInt();


            // 4) 메뉴 변경
            String update = prop.getProperty("updateMenu");
            updatePstmt = con.prepareStatement(update);
            updatePstmt.setString(1, menuName);
            updatePstmt.setInt(2, menuPrice);
            updatePstmt.setInt(3,menuCode);


//             * executeQuery(): Select 문에 사용 결과를 ResultSet으로 반환
//             * executeUpdate(): INSERT, UPDATE, DELETE문에 사용 - 쿼리에 영향받은 행의 수를 반환
            result = updatePstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(selectPstmt);
            close(updatePstmt);
            close(con);
        }
        if (result >0) {
            System.out.println("변경 성공한 행의 수: " + result);
        } else {
            System.out.println("메뉴 변경 실패");
        }
    }
}
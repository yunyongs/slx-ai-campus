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

        System.out.println("=== 메뉴 목록입니다. ===");


        Properties prop = new Properties();
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<MenuDTO> menuList = new ArrayList<>();

        int Result = 1;

        try {
            // Query문 작성된 xml
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu-query.xml"));
            System.out.println("쿼리문 = " + prop.getProperty("selectAllMenu"));

            // 메뉴 리스트 보여주기
            String selectAll = prop.getProperty("selectAllMenu");
            pstmt = con.prepareStatement(selectAll);
            rset = pstmt.executeQuery(selectAll);
            while (rset.next()) {
                menuList.add(
                        new MenuDTO(
                                rset.getInt("menu_code"),
                                rset.getString("menu_name"),
                                rset.getInt("menu_price"),
                                rset.getInt("category_code"),
                                rset.getString("orderable_status")
                        )
                );

            }
            for (MenuDTO dto : menuList) {
                System.out.println(dto);
            }

            Scanner sc = new Scanner(System.in);

            System.out.println("=== 변경할 메뉴의 번호를 입력해 주세요 (menu_code) ===");
            int menuCode = sc.nextInt();

            System.out.println("해당 메뉴의 변경할 이름을 입력해주세요 : ");
            String menuName = sc.nextLine();
            sc.nextLine();

            System.out.println("해당 메뉴 변경할 가격을 입력하세요 : ");
            int menuPrice = sc.nextInt();


            MenuDTO newMenu = new MenuDTO();

            String update = prop.getProperty("updatetMenu");
            pstmt = con.prepareStatement(update);
            pstmt.setString(1, menuName);
            pstmt.setInt(2, menuPrice);
            pstmt.setInt(3,menuCode);

//             * executeQuery(): Select 문에 사용 결과를 ResultSet으로 반환
//             * executeUpdate(): INSERT, UPDATE, DELETE문에 사용 - 쿼리에 영향받은 행의 수를 반환


            Result = pstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
        System.out.println("Result = " + Result);
    }
}






/*






        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

//        System.out.println("result = " + result);
    }
}
        }
*/
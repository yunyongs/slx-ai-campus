package lecture.section2.run.c_preparedStatement;

import lecture.section2.model.MenuDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static lecture.section2.common.JDBCTemplate.getConnection;

public class Application2 {

    public static void main(String[] args) {

        // 사용자에게 문자열을 입력받고
        // 해당 문자열이 포함된 메뉴 목록을 출력하는 프로그램
        // (LIKE)
        Scanner sc = new Scanner(System.in);
        System.out.print("검색할 메뉴명을 입력하세요 : ");
        String keyword = sc.nextLine();

        String query = "select * from tbl_menu where menu_name like concat('%', ?, '%')";

        List<MenuDTO> menuList = new ArrayList<>();

        try(
                Connection con = getConnection(); // 1. 커넥션 만들기
                PreparedStatement pstmt = con.prepareStatement(query); // 2. PreparedStatement 객체만들기
        ) {

            // 쿼리문 완성
            pstmt.setString(1, keyword );

            ResultSet rset = pstmt.executeQuery(); // 쿼리 실행

            while (rset.next()) {
                menuList.add(new MenuDTO(
                        rset.getInt("menu_code"),
                        rset.getString("menu_name"),
                        rset.getInt("menu_price"),
                        rset.getInt("category_code"),
                        rset.getString("orderable_status"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (MenuDTO menu : menuList) {
            System.out.println("menu = " + menu);
        }
    }



}
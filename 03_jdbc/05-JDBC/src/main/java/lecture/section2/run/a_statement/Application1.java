package lecture.section2.run.a_statement;

import lecture.section2.common.JDBCTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application1 {
    public static void main(String[] args) {

        Connection con = JDBCTemplate.getConnection();

        // Statement : 완성된 SQL 문자열을 실행
        Statement stmt = null;

        // ResultSet : SQL의 결과를 행단위로 읽음
        ResultSet rset = null;

        try {
            stmt = con.createStatement();

            String sql = "select menu_code, menu_name from tbl_menu";
            rset = stmt.executeQuery(sql); // sql을 실행시킨 결과물을 ResultSet에 담기

            while (rset.next()){
                System.out.println(rset.getInt("menu_code")); // getInt : 정수형데이터
                System.out.println(rset.getString("menu_name")); // getString: 문자열데이터
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

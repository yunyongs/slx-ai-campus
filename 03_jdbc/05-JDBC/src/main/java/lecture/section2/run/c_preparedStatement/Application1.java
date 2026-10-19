package lecture.section2.run.c_preparedStatement;

import lecture.section2.model.MenuDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lecture.section2.common.JDBCTemplate.getConnection;


public class Application1 {
    public static void main(String[] args) {


        // ? 위치 홀더
        String query = "SELECT * from tbl_menu WHERE menu_code = ?";

        try (
                Connection con = getConnection();
                PreparedStatement pstmt = con.prepareStatement(query);
             )

        {
            int menuCode = 1; // 검색할 메뉴코드
            pstmt.setInt(1, menuCode);
            ResultSet rset = pstmt.executeQuery();

            if (rset.next()) {
                MenuDTO menuDTO = new MenuDTO(
                        rset.getInt("menu_code"),
                        rset.getString("menu_name"),
                        rset.getInt("menu_price"),
                        rset.getInt("category_code"),
                        rset.getString("orderable_status")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



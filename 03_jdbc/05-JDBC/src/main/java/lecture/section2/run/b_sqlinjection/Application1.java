package lecture.section2.run.b_sqlinjection;

import com.sun.jdi.connect.Connector;
import lecture.section2.common.JDBCTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Predicate;

import static lecture.section2.common.JDBCTemplate.getConnection;

public class Application1 {

    //사용자 입력이라고 가정
    private static String menuName = "한우딸기국밥";
    private static String orderableStatus = "'or 1=1 and menu_name = '한우딸기국밥";


    public static void main(String[] args) {

        // 1. 커넥션생성
        Connection con = getConnection();
        // 2. Statement 객체 생성
        Statement stmt = null;
        // 3. ResultSet 객체 생성
        ResultSet rset = null;

        // 4. 쿼리 작성 ( menu name과 주문가능여부를 확인하는 쿼리)
        String query =
                "select * from tbl_menu where menu_name = '"
                        + menuName
                        + "' and orderable_status = '" + orderableStatus + "'";

        System.out.println(query);
        // 5. Statement를 이용해서 쿼리 실행
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()) {
                System.out.println("주문가능 메뉴 " + menuName);
            } else {
                System.out.println("주문가능한 메뉴가 아닙니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
   /*         close(rset);
            close(stmt);
            close(con);
*/
        }


        // 6. ResultSet에서 결과를 꺼내기 (가공)
    }
}
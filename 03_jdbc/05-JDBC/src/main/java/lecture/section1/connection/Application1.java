package lecture.section1.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application1 {

    // Connection 만들기
    public static void main(String[] args) {
        // Connection : DB 접속을 위한 연결 객체 (Statement 생성하기 위해)
        Connection con = null;

        try {
            // 드라이버 등록
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/menudb",
                    "bear",
                    "bear"
            );

            System.out.println("con = " + con);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(con != null) {
                // JDBC 커넥션 연결 해제
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

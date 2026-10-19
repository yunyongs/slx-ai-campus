package lecture.section2.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

// DB 연결 생성과 JDBC 자원 반납을 처리하는 클래스
public class JDBCTemplate {

    public static Connection getConnection() {
        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader(
                    "/Users/yuny/repos/slx-ai-campus/03_jdbc/05-JDBC/src/main/java/lecture/section2/config/db.properties"));

            System.out.println("설정 파일을 읽어왔습니다 ...");

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(
                    url,
                    prop
            );

            System.out.println("커넥션 생성이 완료되었습니다.");

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }

        return con;
    }

    // 커넥션 닫기
    public static void close(Statement stmt) {

        try {
            if(stmt != null & !stmt.isClosed()) {
                stmt.close();;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rset) {

        try {
            if(rset != null & !rset.isClosed()) {
                rset.close();;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package common;

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
                    "src/main/java/config/db.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(
                    url,
                    prop
            );

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

    public static void close(Connection connection) {

        try {
            if(connection != null & !connection.isClosed()) {
                connection.close();;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

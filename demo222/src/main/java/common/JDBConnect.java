package common;

import java.sql.*;

public class JDBConnect {
  public Connection con;
  public Statement stmt;
  public PreparedStatement psmt;
  public ResultSet rs;

  // 기본 생성자
  public JDBConnect() {
    try {
      // JDBC 드라이버 로드
      Class.forName("org.mariadb.jdbc.Driver");

      // DB에 연결
      String url = "jdbc:mariadb://localhost:3306/musthave";
      String id = "root";
      String pwd = "12345";
      con = DriverManager.getConnection(url, id, pwd);

      System.out.println("DB 연결 성공(기본 생성자)");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 연결 해제(자원 반납)
  public void close() {
    try {
      if (rs != null) rs.close();
      if (stmt != null) stmt.close();
      if (psmt != null) psmt.close();
      if (con != null) con.close();

      System.out.println("JDBC 자원 해제");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}

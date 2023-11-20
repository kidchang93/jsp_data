package servlet;

import membership.MemberDAO;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberAuth extends HttpServlet {
  private static final long serialVersionUID = 1L;
  MemberDAO dao;

  public void init() throws ServletException{
    // application 내장 객체 얻기
    ServletContext application = this.getServletContext();

    // web.xml 에서 DB 연결 정보 얻기
    String driver =application.getInitParameter("MySQL_Driver");
    String connectUrl =application.getInitParameter("MySQL_URL");
    String mId =application.getInitParameter("MySQL_Id");
    String mPass =application.getInitParameter("MySQL_Pwd");

    // DAO 생성
    dao = new MemberDAO(driver, connectUrl, mId, mPass);

  }

  @Override
  protected void service (HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    super.doGet(req, resp);
  }
}

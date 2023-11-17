package utils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import java.io.PrintWriter;

public class JSFunction {

  // 메시지 알림창을 띄운 후 명시한 URL로 이동합니다.
//  public static void alertLocation(String msg, String url, JspWriter out) {
//    try {
//
//      String script = "" // 삽입할 자바 스크립트 코드
//              + "<script>"
//              + "  alert('" + msg + "');"
//              + "  location.href='" + url + "';"
//              + "</script>";
//      out.print(script); // 자바 스크립트 코드를 out 내장 객체로 출력(삽입)
//    } catch (Exception e) {
//    }
//  }
//
//  // 메시지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
//  public static void alertBack(String msg, JspWriter out) {
//    try {
//
//      String script = "" // 삽입할 자바 스크립트 코드
//              + "<script>"
//              + "  alert('" + msg + "');"
//              + "  history.back();"
//              + "</script>";
//      out.print(script);
//    } catch (Exception e) {
//    }


    // 메시지 알림창을 띄운 후 명시한 URL로 이동합니다.
    public static void alertLocation (HttpServletResponse resp, String msg, String url){
      try {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        String script = "<script>"
                + "    alert('" + msg + "');"
                + "    location.href='" + url + "';"
                + "</script>";
        writer.print(script);
      } catch (Exception e) {
      }
    }

    // 메시지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
    public static void alertBack (HttpServletResponse resp, String msg){
      try {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        String script = "<script>"
                + "    alert('" + msg + "');"
                + "    history.back();"
                + "</script>";
        writer.print(script);
      } catch (Exception e) {
      }
    }
  }


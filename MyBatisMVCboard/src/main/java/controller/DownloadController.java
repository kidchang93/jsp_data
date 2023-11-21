package controller;

import Util.FileUtil;
import model.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/download.do")
public class DownloadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 요청 파라미터에서 파일명, 저장된 파일명, 게시물 인덱스 추출
        String ofile = req.getParameter("ofile");
        String sfile = req.getParameter("sfile");
        String idx = req.getParameter("idx");

        // 파일 다운로드를 위해 FileUtil의 download 메서드 호출
        FileUtil.download(req, resp, "/Uploads", sfile, ofile);
        // 게시판 DAO 객체 생성
        BoardDAO dao = new BoardDAO();
        // 다운로드 횟수 증가 메서드 호출
        dao.downCountPlus(idx);
    }
}

package controller;

import Util.Encrypt;
import Util.FileUtil;
import Util.JSFunction;
import model.BoardDAO;
import model.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/pass.do")
public class PassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 요청 파라미터에서 모드 추출하여 request 영역에 저장
        req.setAttribute("mode",req.getParameter("mode"));
        // Pass.jsp로 포워드
        req.getRequestDispatcher("/board/Pass.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 매개변수 저장
        String idx = req.getParameter("idx");
        String mode = req.getParameter("mode");
        String pass = Encrypt.getEncrypt(req.getParameter("pass"));

        // 비밀번호 확인
        BoardDAO dao = new BoardDAO();
        boolean confirmed = dao.confirmPassword(pass,idx);

//        System.out.println("confirmed=========" + confirmed);


        if (confirmed) {  // 비밀번호 일치
            if (mode.equals("edit")) {  // 수정 모드
                // 세션에 비밀번호 저장 후 수정 페이지로 이동
                HttpSession session = req.getSession();
                session.setAttribute("pass", pass);
                resp.sendRedirect("../board/edit.do?idx=" + idx);
            }
            else if (mode.equals("delete")) {  // 삭제 모드
                dao = new BoardDAO();
                BoardVO vo = dao.selectView(idx);
                int result = dao.deletePost(idx);  // 게시물 삭제
                if (result == 1) {  // 게시물 삭제 성공 시 첨부파일도 삭제
                    String saveFileName = vo.getSfile();
                    FileUtil.deleteFile(req, "/Uploads", saveFileName);
                }

                // 자바스크립트 함수 실행.
                JSFunction.alertLocation(resp, "삭제되었습니다.", "../board/list.do");
            }
        }
        else {  // 비밀번호 불일치
            JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");

        }
    }
}

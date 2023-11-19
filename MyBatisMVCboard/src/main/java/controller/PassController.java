package controller;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mode",req.getParameter("mode"));
        req.getRequestDispatcher(req.getContextPath()+"/board/Pass.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idx = req.getParameter("idx");
        String mode = req.getParameter("mode");
        String pass = req.getParameter("pass");

        BoardDAO dao = new BoardDAO();
        boolean confirmed = dao.confirmPassword(pass,idx);

        if (confirmed) {
            if (mode.equals("edit")){
                HttpSession session = req.getSession();
                session.setAttribute("pass",pass);
                resp.sendRedirect(req.getContextPath() + "/board/edit.do?idx=" + idx);
            } else if (mode.equals("delete")) {
                BoardVO vo = new BoardVO();
                int result = dao.deletePost(idx);
                if (result == 1) {
                    String saveFileName = vo.getSfile();
                    FileUtil.deleteFile(req,"/Uploads", saveFileName);
                }
                JSFunction.alertLocation(resp,"삭제되었습니다.",req.getContextPath() + "/board/list.do");
            } else {
                JSFunction.alertBack(resp,"비밀번호가 일치하지 않습니다.");
            }
        }
    }
}

package controller;

import Util.FileUtil;
import Util.JSFunction;
import model.BoardDAO;
import model.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/board/edit.do")
@MultipartConfig (
        maxFileSize = 1024 * 1024 * 1,
        maxRequestSize = 1024 * 1024 * 20
)
public class EditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idx = req.getParameter("idx");
        BoardDAO dao = new BoardDAO();
        BoardVO vo = dao.selectView(idx);
        req.setAttribute("vo",vo);
        req.getRequestDispatcher(req.getContextPath() + "/board/edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String saveDirectory = req.getServletContext().getRealPath("/Uploads");

        String originalFileName = "";
        try {
            originalFileName = FileUtil.uploadFile(req,saveDirectory);
        } catch (Exception e) {
            System.out.println("업데이트중 파일 업로드 오류 발생");
            JSFunction.alertBack(resp,"게시물 수정 중 파일 업로드 오류입니다.");
            return;
        }
        String idx =req.getParameter("idx");
        String prevOfile = req.getParameter("prevOfile");
        String prevSfile = req.getParameter("prevSfile");
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        HttpSession session = req.getSession();
        String pass = (String) session.getAttribute("pass");

        BoardVO vo = new BoardVO();
        vo.setIdx(idx);
        vo.setName(name);
        vo.setTitle(title);
        vo.setContent(content);
        vo.setPass(pass);

        if (originalFileName != ""){
            String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
            vo.setOfile(originalFileName);
            vo.setSfile(savedFileName);
            FileUtil.deleteFile(req,"/Uploads",prevSfile);
        } else {
            vo.setOfile(prevOfile);
            vo.setSfile(prevSfile);
        }

        BoardDAO dao = new BoardDAO();
        int result = dao.updatePost(vo);

        if (result == 1) {
            session.removeAttribute("pass");
            resp.sendRedirect(req.getContextPath()+ "/board/view.do?idx=" + idx);
        } else {
            JSFunction.alertLocation(resp,"비밀번호가 일치하지 않아서 업데이트 할 수 없습니다.",
                    req.getContextPath() + "/board/view.do?idx=" + idx);
        }
    }
}

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
import java.io.IOException;

@MultipartConfig(
    maxFileSize = 1024 * 1024 *1,
    maxRequestSize = 1024 * 1024 * 10
)
@WebServlet
public class WriteController extends HttpServlet {
private BoardDAO dao = new BoardDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/board/write.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String saveDirectory = req.getServletContext().getRealPath("/Uploads");
        String originalFileName = "";

        try {
            originalFileName = FileUtil.uploadFile(req,saveDirectory);
        } catch (Exception e){
            System.out.println("파일 업로드 실패!");
            JSFunction.alertLocation(resp,"파일 업로드 오류 발생",req.getContextPath()+"/board/write.do" );
            return;
        }
        BoardVO vo = new BoardVO();
        vo.setName(req.getParameter("name"));
        vo.setTitle(req.getParameter("title"));
        vo.setContent(req.getParameter("content"));
        vo.setPass(req.getParameter("pass"));

        if (originalFileName != null && !originalFileName.equals("")) {
            String savedFileName =FileUtil.renameFile(saveDirectory, originalFileName);
            vo.setOfile(originalFileName);
            vo.setSfile(savedFileName);
        }
        int result = dao.insertWrite(vo);
        if (result == 1){
            resp.sendRedirect(req.getContextPath() + "/board/list.do");
        } else {
            System.out.println("글쓰기 실패");
            JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", req.getContextPath() + "/board/write.do");
        }
    }
}

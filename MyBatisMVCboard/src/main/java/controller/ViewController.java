package controller;

import model.BoardDAO;
import model.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/board/view.do")
public class ViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDAO dao = new BoardDAO();
        String idx = req.getParameter("idx");
        dao.updateVisitCount(idx);
        BoardVO vo = dao.selectView(idx); // idx 에 해당하는게 없다면 null 반환, 에러는 없음
        vo.setContent(vo.getContent().replaceAll("\r\n","<br />"));

        String ext = null;
        String fileName = vo.getSfile();

        if (fileName != null){
            ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        }

        String[] mimeStr = {"png","jpg","gif","jpeg"};
        List<String> mimeList = Arrays.asList(mimeStr);
        boolean isImage = false;
        if (mimeList.contains(ext)){
            isImage = true;
        }

        req.setAttribute("vo",vo);
        req.setAttribute("isImage",isImage);
        req.getRequestDispatcher("/board/View.jsp").forward(req,resp);
    }
}

package controller;

import model.BoardDAO;
import model.BoardVO;
import paging.Criteria;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/board/list.do")
public class ListController extends HttpServlet {

//    private BoardDAO dao = new BoardDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDAO dao = new BoardDAO();
        Map<String, Object> map = new HashMap<>();
        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");

        System.out.println("searchField = "+searchField);
        System.out.println("searchWord = "+searchWord);
        if (searchWord != null && searchWord.trim().equals("")){
            map.put("searchField",searchField);
            map.put("searchWord",searchWord);
        }

        int totalCount = dao.selectCount(map);
        List<BoardVO> boardLists = dao.selectListPage(map);  // 게시물 목록 받기

        /* 페이지 처리 start */

    }



}

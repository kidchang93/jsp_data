package controller;

import model.BoardDAO;
import model.BoardVO;
import paging.Criteria;
import paging.PageMaker;

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
//        List<BoardVO> boardLists = dao.selectListPage(map);  // 게시물 목록 받기

        /* 페이지 처리 start */
        String pageNum = req.getParameter("pageNum");
        Criteria cri = new Criteria();
        // Default 값으로 1을 준다.
        int pageNumInt = 1;
        if (pageNum != null && !pageNum.equals("")){
            try {
                pageNumInt = Integer.parseInt(pageNum.trim());
            } catch (Exception e) {
                System.out.println("숫자로 변환하지 못함");
            }
        }
        //
        cri.setPageNum(pageNumInt);

        // 게시물의 수에 따라 페이지 수 결정하는 함수
        map.put("pageNum",(cri.getPageNum() - 1) * 10);
        List<BoardVO> boardLists = dao.getListWithPaging(map);

        System.out.println("boardLists is null ? = " + boardLists);
        System.out.println("boardLists.size() = " + boardLists != null ? boardLists.size() : "null이기 때문에 size X");

        PageMaker pageMaker = new PageMaker(cri, totalCount);
        req.setAttribute("pageMaker", pageMaker);
        req.setAttribute("boardLists", boardLists);
        map.remove("pageNum");
        req.setAttribute("map", map);
        req.getRequestDispatcher("/board/List.jsp").forward(req, resp);
    }



}

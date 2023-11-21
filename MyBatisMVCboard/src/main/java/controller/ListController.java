package controller;

import model.BoardDAO;
import model.BoardVO;
import paging.BoardPage;


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

        System.out.println("searchField = " + searchField);
        System.out.println("searchWord = " + searchWord);
        if (searchWord != null && searchWord.trim().equals("")) {
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }

        int totalCount = dao.selectCount(map);
//        List<BoardVO> boardLists = dao.selectListPage(map);  // 게시물 목록 받기

        // 페이지 처리 Start
        ServletContext application = getServletContext();
        int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
        int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

        // 현재 페이지 확인
        int pageNum = 1;  // 기본값
        String pageTemp = req.getParameter("pageNum");
        if (pageTemp != null && !pageTemp.equals(""))
            pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정

        // 목록에 출력할 게시물 범위 계산
        int start = (pageNum - 1) * pageSize + 1;  // 첫 게시물 번호
        int end = pageNum * pageSize; // 마지막 게시물 번호
        map.put("start", start);
        map.put("end", end);
        /* 페이지 처리 end */

        List<BoardVO> boardLists = dao.selectListPage(map);  // 게시물 목록 받기

        // 뷰에 전달할 매개변수 추가
        String pagingImg = BoardPage.pagingStr(totalCount, pageSize,
                blockPage, pageNum, "../board/list.do");  // 바로가기 영역 HTML 문자열
        map.put("pagingImg", pagingImg);
        map.put("totalCount", totalCount);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);

        // 전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드
        req.setAttribute("boardLists", boardLists);
        req.setAttribute("map", map);
        req.getRequestDispatcher("/board/List.jsp").forward(req, resp);
    }

}

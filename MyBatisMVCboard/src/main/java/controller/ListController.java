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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 게시판 DAO 객체 생성
        BoardDAO dao = new BoardDAO();
        // 검색 조건 및 검색어를 담을 Map 객체 생성
        Map<String, Object> map = new HashMap<>();
        // 요청 파라미터에서 검색 필드와 검색어 추출
        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");

//        System.out.println("searchField = " + searchField);
//        System.out.println("searchWord = " + searchWord);

        // 검색어가 존재하는 경우, Map에 추가
        if (searchWord != null && !searchWord.trim().equals("")) {
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }

//        System.out.println("searchField...."+searchField);
//        System.out.println("searchWord...."+searchWord);

        // DAO를 통해 전체 게시물 수 조회
        int totalCount = dao.selectCount(map);

        // 페이지 처리 Start
        // ServletContext 객체를 통해 웹 애플리케이션의 초기 파라미터 값 가져오기
        ServletContext application = getServletContext();
        int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
        int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

//        System.out.println("======================");
//        System.out.println("pageSize...."+pageSize);
//        System.out.println("blockPage...."+blockPage);

        // 현재 페이지 확인
        int pageNum = 1;  // 기본값
        String pageTemp = req.getParameter("pageNum");
        if (pageTemp != null && !pageTemp.equals(""))
            pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정

//        System.out.println("pageTemp......" + pageTemp);
//        System.out.println("New pageNum .... "+ pageNum);

        // 목록에 출력할 게시물 범위 계산
        int start = (pageNum - 1) * pageSize + 1;  // 첫 게시물 번호
        int end = pageNum * pageSize; // 마지막 게시물 번호
        // map 에 키와 값 추가
        map.put("start", start);
        map.put("end", end);
        /* 페이지 처리 end */

//        System.out.println("start...."+start);
//        System.out.println("end...."+end);


        // 뷰에 전달할 매개변수 추가
        String pagingImg = BoardPage.pagingStr(totalCount, pageSize,
                blockPage, pageNum,"../board/list.do" ,searchField, searchWord );  // 바로가기 영역 HTML 문자열
        // map 에 키와 값 추가
        map.put("pagingImg", pagingImg);
        map.put("totalCount", totalCount);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);

//        System.out.println("totalCount...."+ totalCount);
//        System.out.println("pageNum...." + pageNum);
//        System.out.println("pageSize...." + pageSize);
//        System.out.println("pagingImg .... " + pagingImg);

        // 게시물 목록을 페이징 처리하여 가져오기
        List<BoardVO> boardPagingLists = dao.getListWithPaging(map); // 페이징 처리

        // 전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드
        req.setAttribute("boardPagingLists", boardPagingLists);
        req.setAttribute("map", map);
        req.getRequestDispatcher("/board/List.jsp").forward(req, resp);
    }

}

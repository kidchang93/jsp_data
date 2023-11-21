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
        // 요청 파라미터에서 게시물 인덱스 추출
        String idx = req.getParameter("idx");
        // 조회수 증가 메서드 호출
        dao.updateVisitCount(idx);
        // 게시물 조회 메서드 호출
        BoardVO vo = dao.selectView(idx);
        // 게시물의 줄바꿈 처리 (개행 문자를 <br />로 변경)
        vo.setContent(vo.getContent().replaceAll("\r\n","<br />"));

        // 파일 확장자 및 이미지 여부 확인
        String ext = null;
        String fileName = vo.getSfile();

        // 파일 이름에서 확장자 추출
        if (fileName != null){
            ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        // 이미지 파일의 확장자들을 담고 있는 문자열 배열
        String[] mimeStr = {"png","jpg","gif","jpeg"};
        // 배열을 리스트로 변환
        List<String> mimeList = Arrays.asList(mimeStr);
        // 확장자를 통해 파일이 이미지인지 여부 확인
        boolean isImage = false;
        // 확장자가 이미지 확장자 리스트에 포함되어 있는지 확인
        if (mimeList.contains(ext)){
            isImage = true;
        }

        // 조회된 게시물 및 관련 정보를 request 영역에 저장
        req.setAttribute("vo",vo);
        req.setAttribute("isImage",isImage);
        // View.jsp로 포워드
        req.getRequestDispatcher("/board/View.jsp").forward(req,resp);
    }
}

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
@WebServlet("/board/write.do")
public class WriteController extends HttpServlet {
    // 게시판 객체 생성
private BoardDAO dao = new BoardDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Write.jsp로 포워드
        req.getRequestDispatcher("/board/Write.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 파일 업로드를 위한 디렉토리 설정
        String saveDirectory = req.getServletContext().getRealPath("/Uploads");
        String originalFileName = "";

        try {
            // 파일 업로드 시도하고, 업로드된 파일명을 반환
            originalFileName = FileUtil.uploadFile(req,saveDirectory);
        } catch (Exception e){
            // 파일 업로드 중 오류 발생 시 처리
            System.out.println("파일 업로드 실패!");
            JSFunction.alertLocation(resp,"파일 업로드 오류 발생",req.getContextPath()+"/board/write.do" );
            return;
        }

        // BoardVO 객체 생성 및 필드 설정 ( 이부분은 VO로 처리 가능한지 아직 파악 못함
        // 뇌피셜로 여기는 파라미터값을 가져온 상태에서 저장하는 vo이기 때문에 따로 빼서 작성해야 정상 작동됨.
        BoardVO vo = new BoardVO();
        vo.setName(req.getParameter("name"));
        vo.setTitle(req.getParameter("title"));
        vo.setContent(req.getParameter("content"));
        vo.setPass(req.getParameter("pass"));

        // 파일이 업로드되었을 경우, 파일명 설정
        if (originalFileName != null && !originalFileName.equals("")) {
            String savedFileName =FileUtil.renameFile(saveDirectory, originalFileName);
            vo.setOfile(originalFileName);
            vo.setSfile(savedFileName);
        }
        // 게시물 작성 메서드 호출
        int result = dao.insertWrite(vo);
        if (result == 1){
            // 글쓰기 성공 시 목록 페이지로 이동
            resp.sendRedirect(req.getContextPath() + "/board/list.do");
        } else {
            // 글쓰기 실패 시 경고창 출력 및 이전 페이지로 이동
            System.out.println("글쓰기 실패");
            JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", req.getContextPath() + "/board/write.do");
        }
    }
}

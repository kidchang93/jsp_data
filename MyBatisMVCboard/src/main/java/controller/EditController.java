package controller;

import Util.Encrypt;
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
// 멀티파트를 이용해 파일규격을 결정.
@MultipartConfig (
        maxFileSize = 1024 * 1024 * 1,
        maxRequestSize = 1024 * 1024 * 20
)
public class EditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 요청 파라미터에서 게시물 인덱스 추출
        String idx = req.getParameter("idx");
        // 게시판 DAO 객체 생성
        BoardDAO dao = new BoardDAO();
        // 게시물 인덱스를 이용하여 해당 게시물 정보 조회
        BoardVO vo = dao.selectView(idx);
        // 조회된 게시물 정보를 request 영역에 저장
        req.setAttribute("vo",vo);
        // Edit.jsp로 포워드
        req.getRequestDispatcher("/board/Edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 파일 업로드를 위해 디렉토리 설정.
        String saveDirectory = req.getServletContext().getRealPath("/Uploads");

        // 1. 파일 업로드 처리 =============================
        // 파일 업로드를 시도하고, 업로드된 파일명을 반환
        String originalFileName = "";
        try {
            originalFileName = FileUtil.uploadFile(req,saveDirectory);

            // 파일 업로드 중 오류 발생 시 처리
        } catch (Exception e) {
            System.out.println("업데이트중 파일 업로드 오류 발생");
            JSFunction.alertBack(resp,"게시물 수정 중 파일 업로드 오류입니다.");
            return;
        }

        // 요청 파라미터에서 필요한 값들 추출
        String idx =req.getParameter("idx");
        String prevOfile = req.getParameter("prevOfile");
        String prevSfile = req.getParameter("prevSfile");
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        // 세션에서 비밀번호 추출
        HttpSession session = req.getSession();
        // 암호화 테스트
        String pass = Encrypt.getEncrypt((String)session.getAttribute("pass"));

        // BoardVO 객체 생성 및 필드 설정
        BoardVO vo = new BoardVO();
        vo.setIdx(idx);
        vo.setName(name);
        vo.setTitle(title);
        vo.setContent(content);
        vo.setPass(Encrypt.getEncrypt(pass));

        // 파일이 업로드되었을 경우, 파일명 설정 및 기존 파일 삭제
        if (originalFileName != "" && !originalFileName.equals("") && !originalFileName.isEmpty()){
            String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
            vo.setOfile(originalFileName);
            vo.setSfile(savedFileName);
            FileUtil.deleteFile(req,"/Uploads",prevSfile);
        } else {
            // 파일이 업로드되지 않았을 경우, 기존 파일명 설정
            vo.setOfile(prevOfile);
            vo.setSfile(prevSfile);
        }

        // 게시판 DAO 객체 생성
        BoardDAO dao = new BoardDAO();
        // 게시물 업데이트 수행
        int result = dao.updatePost(vo);

        if (result == 1) {
            // 업데이트 성공 시, 세션에서 비밀번호 제거하고 해당 게시물로 이동
            session.removeAttribute("pass");
            resp.sendRedirect(req.getContextPath()+ "/board/view.do?idx=" + idx);
        } else {
            // 업데이트 실패 시, 경고창 출력 및 이전 페이지로 이동
            JSFunction.alertLocation(resp,"비밀번호가 일치하지 않아서 업데이트 할 수 없습니다.",
                    req.getContextPath() + "/board/view.do?idx=" + idx);
        }
    }
}

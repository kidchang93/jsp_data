package Util;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {
    //파일 업로드
    public static String uploadFile(HttpServletRequest req, String sDirectory)
            throws ServletException, IOException {
        // 지정된 디렉토리 및 상위 디렉토리가 존재하는지 확인하고, 없는 경우 생성합니다.
        Path saveDirectoryPath = Paths.get(sDirectory);
        Files.createDirectories(saveDirectoryPath);

        // 요청에서 파일 파트를 검색합니다.
        Part part = req.getPart("ofile");

        // 파일 파트의 content-disposition 헤더에서 원본 파일 이름을 추출합니다.
        String partHeader = part.getHeader("content-disposition");
        System.out.println("partHeader=" + partHeader);

        // content-disposition 헤더를 분할하여 파일 이름을 가져오고, 주변 따옴표를 제거합니다.
        String[] phArr = partHeader.split("filename=");
        String originalFileName = phArr[1].trim().replace("\"", "");

        // 원본 파일 이름이 비어 있지 않은 경우, 파일을 지정된 디렉토리에 작성합니다.
        if (!originalFileName.isEmpty()) {
            part.write(sDirectory + File.separator + originalFileName);
        }
        // 업로드된 파일의 이름을 반환합니다.
        return originalFileName;
    }

    public static String renameFile(String sDirectory, String fileName) {
        String originalNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
        String ext = fileName.substring(fileName.lastIndexOf("."));
        String now = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.KOREA).format(new Date());

        String newFileName = now + "__" + originalNameWithoutExtension + ext;

        //기존 파일명을 새로운 파일명으로 변경
        File oldFile = new File(sDirectory + File.separator + fileName);
        File newFile = new File(sDirectory + File.separator + newFileName);
        oldFile.renameTo(newFile);

        return newFileName;
    }

    public static void download(HttpServletRequest req, HttpServletResponse resp, String directory, String sfileName, String ofileName) {
        String sDirectory = req.getServletContext().getRealPath(directory);
        try {
            // parent, child
            File file = new File(sDirectory, sfileName);
            InputStream inputStream = new FileInputStream(file);

            // 한글 파일명 깨짐 방지
            String client = req.getHeader("User-Agent");
            if (client.indexOf("WOW64") == -1) {
                ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
            } else {
                ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            resp.reset();
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition",
                    "attachment; filename=\"" + ofileName + "\"");
            resp.setHeader("Content-Length", "" + file.length());

            ServletOutputStream oStream = resp.getOutputStream();
            byte[] b = new byte[(int)file.length()];
            int readBuffer = 0;
            while ((readBuffer = inputStream.read(b)) > 0) {
                oStream.write(b, 0, readBuffer);
            }
            inputStream.close();
            oStream.flush();
            oStream.close();
        } catch (Exception e) {
            System.out.println("파일 다운로드 중 오류 발생...");
        }
    }

    public static void deleteFile(HttpServletRequest req, String directory, String filename) {
        String sDirectory = req.getServletContext().getRealPath(directory);
        File file = new File(sDirectory + File.separator + filename);
        if (file.exists()) {
            file.delete();
        }
    }
}

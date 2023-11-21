package paging;

public class BoardPage {
    public static String pagingStr(int totalCount, int pageSize, int blockPage,
                                   int pageNum, String reqUrl) {
        // 반환할 페이지 네비게이션 문자열 초기화
        String pagingStr = "";

        // 단계 3 : 전체 페이지 수 계산
        int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));

        // 단계 4 : '이전 페이지 블록 바로가기' 출력
        int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
        if (pageTemp != 1) {
            // 첫 페이지로 이동하는 링크 추가
            pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
            pagingStr += "&nbsp;"; // 공백 추가
            // 이전 블록으로 이동하는 링크 추가
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1)
                    + "'>[이전 블록]</a>";
        }

        // 단계 5 : 각 페이지 번호 출력
        int blockCount = 1;
        while (blockCount <= blockPage && pageTemp <= totalPages) {
            if (pageTemp == pageNum) {
                // 현재 페이지는 링크를 걸지 않고 공백으로만 출력
                pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
            } else {
                // 다른 페이지는 해당 페이지로 이동하는 링크 추가
                pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp
                        + "'>" + pageTemp + "</a>&nbsp;";
            }
            pageTemp++;
            blockCount++;
        }

        // 단계 6 : '다음 페이지 블록 바로가기' 출력
        if (pageTemp <= totalPages) {
            // 다음 블록으로 이동하는 링크 추가
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp
                    + "'>[다음 블록]</a>";
            pagingStr += "&nbsp;"; // 공백 추가
            // 마지막 페이지로 이동하는 링크 추가
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages
                    + "'>[마지막 페이지]</a>";
        }

        // 최종적으로 조립된 페이지 네비게이션 문자열 반환
        return pagingStr;
    }
}
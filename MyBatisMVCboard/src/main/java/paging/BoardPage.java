package paging;


/*
 * 주어진 페이지 관련 정보를 바탕으로 페이징 처리를 수행하는 유틸리티 메서드.
 * @param totalCount 전체 항목 수
 * @param pageSize 한 페이지에 보여질 항목 수
 * @param blockPage 한 블록에 보여질 페이지 수
 * @param pageNum 현재 페이지 번호
 * @param reqUrl 페이지 링크가 걸릴 URL
 * @return 페이지 네비게이션을 나타내는 HTML 문자열
 */
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
        System.out.println("pageTemp...." + pageTemp);
        System.out.println("blockCount...."+ blockCount);

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
        System.out.println("pagingStr...." + pagingStr);
        return pagingStr;

    }
}
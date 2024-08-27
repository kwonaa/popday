package utils;

public class PopDayPage {
//    public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl, String searchField, String searchWord) {
//        String pagingStr = "";
//        
//        // 전체 페이지 수
//        int totalPages = (int) (Math.ceil((double) totalCount / pageSize));
//
//        // 페이지 블록 시작하는 번호 구하기
//        int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
//
//        // 검색 조건이 있을 경우 URL에 포함시키기
//        String searchParam = "";
//        if (searchWord != null && !searchWord.isEmpty()) {
//            searchParam = "&searchField=" + searchField + "&searchWord=" + searchWord;
//        }
//
//        // 첫 페이지, 이전 블록 이동 링크
//        if (pageTemp != 1) {
//            pagingStr += "<a href='" + reqUrl + "?pageNum=1" + searchParam + "'>[첫 페이지]</a> ";
//            pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1) + searchParam + "'>[이전 블록]</a>";
//        }
//
//        // 페이지 블록 출력
//        int blockCount = 1;
//        while (blockCount <= blockPage && pageTemp <= totalPages) {
//            if (pageTemp == pageNum) {
//                pagingStr += " " + pageTemp + " ";
//            } else {
//                pagingStr += " <a href='" + reqUrl + "?pageNum=" + pageTemp + searchParam + "'>" + pageTemp + "</a> ";
//            }
//            pageTemp++;
//            blockCount++;
//        }
//
//        // 다음 블록, 마지막 페이지 이동 링크
//        if (pageTemp <= totalPages) {
//            pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp + searchParam + "'>[다음 블록]</a> ";
//            pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages + searchParam + "'>[마지막 페이지]</a>";
//        }
//
//        return pagingStr;
//    }
	
    public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl, String searchField, String searchWord) {
        // 페이징 관련 코드를 모두 주석 처리하고 빈 문자열 반환
        // 페이징 처리와 관련된 기능이 제거되어 모든 게시물이 표시됩니다.
        return "";
    }	
}

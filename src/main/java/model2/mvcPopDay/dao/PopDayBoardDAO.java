package model2.mvcPopDay.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBConnPool;
import model2.mvcPopDay.dto.PopDayBoardDTO;

public class PopDayBoardDAO extends DBConnPool {
    // DB 연결
    public PopDayBoardDAO() { // public DBConnPool() 호출
    }

    // 전체 글 수
    public int selectCount(Map<String, Object> map) {
        int totalCount = 0;
        String query = "select count(*) from board";

        // 검색 조건 추가 (selectCount)
        if (map.get("searchWord") != null) {
            query += " where " + map.get("searchField") + " "
                    + " like '%" + map.get("searchWord") + "%'";
        }

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                totalCount = rs.getInt(1); // count(*)을 의미
            }
        } catch (Exception e) {
            System.out.println("게시물 카운트 중 예외 발생");
            e.printStackTrace();
        }
        return totalCount;
    }

    // 목록. 페이징 처리
//    public List<PopDayBoardDTO> selectListPage(Map<String, Object> map) {
//        List<PopDayBoardDTO> board = new ArrayList<PopDayBoardDTO>();
//        String query = " "
//                + "select * from ( "
//                + "    select tb.*, rownum rNum from ( "
//                + "        select * from board ";
//
//        if (map.get("searchWord") != null) {
//            query += " where to_char(sdate, 'MM') = ? "; // 월 필터링을 위한 조건
//        }
//
//        // 월별 필터 조건 추가
//        if (map.get("cno") != null) {
//            query += " and cno = " + map.get("cno");
//        }
//
//        query += "    order by no desc "
//                + "    ) tb "
//                + " ) "
//                + " where rNum between ? and ?";
//
//        try {
//            psmt = con.prepareStatement(query);
//            if (map.get("searchWord") != null) {
//                psmt.setString(1, map.get("searchWord").toString()); // 월 필터링
//                psmt.setString(2, map.get("start").toString());
//                psmt.setString(3, map.get("end").toString());
//            } else {
//                psmt.setString(1, map.get("start").toString());
//                psmt.setString(2, map.get("end").toString());
//            }
//            rs = psmt.executeQuery();
//
//            while (rs.next()) {
//                PopDayBoardDTO bdto = new PopDayBoardDTO();
//
//                bdto.setNo(rs.getString("no"));
//                bdto.setUserid(rs.getString("userid"));
//                bdto.setTitle(rs.getString("title"));
//                bdto.setSdate(rs.getDate("sdate"));
//                bdto.setEdate(rs.getDate("edate"));
//                bdto.setLocation(rs.getString("location"));
//                bdto.setContent(rs.getString("content"));
//                bdto.setPostdate(rs.getDate("postdate"));
//                bdto.setOfile(rs.getString("ofile"));
//                bdto.setSfile(rs.getString("sfile"));
//                bdto.setCno(rs.getInt("cno"));
//
//                board.add(bdto);
//            }
//        } catch (Exception e) {
//            System.out.println("게시물 조회 중 예외 발생");
//            e.printStackTrace();
//        }
//        return board;
//    }
    
 // 목록. 페이징 처리 제거
    public List<PopDayBoardDTO> selectListPage(Map<String, Object> map) {
        List<PopDayBoardDTO> board = new ArrayList<PopDayBoardDTO>();
        String query = " "
                + "select * from board "; // 페이징 관련 서브쿼리 및 rownum 제거

        // 월 필터링 조건 추가
        if (map.get("searchWord") != null) {
            query += " where to_char(sdate, 'MM') = ? "; // 월 필터링을 위한 조건
        }

        // 월별 필터 조건 추가
        if (map.get("cno") != null) {
            query += " and cno = " + map.get("cno");
        }

        query += " order by no desc "; // 정렬 조건 추가

        try {
            psmt = con.prepareStatement(query);
            int paramIndex = 1;
            
            // 월 필터링 조건이 있을 때
            if (map.get("searchWord") != null) {
                psmt.setString(paramIndex++, map.get("searchWord").toString()); // 월 필터링
            }
            
            rs = psmt.executeQuery();

            while (rs.next()) {
                PopDayBoardDTO bdto = new PopDayBoardDTO();

                bdto.setNo(rs.getString("no"));
                bdto.setUserid(rs.getString("userid"));
                bdto.setTitle(rs.getString("title"));
                bdto.setSdate(rs.getDate("sdate"));
                bdto.setEdate(rs.getDate("edate"));
                bdto.setLocation(rs.getString("location"));
                bdto.setContent(rs.getString("content"));
                bdto.setPostdate(rs.getDate("postdate"));
                bdto.setOfile(rs.getString("ofile"));
                bdto.setSfile(rs.getString("sfile"));
                bdto.setCno(rs.getInt("cno"));

                board.add(bdto);
            }
        } catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }
        return board;
    }
    


    // C
    public int insertWrite(PopDayBoardDTO bdto) {
        int result = 0;
        try {
            String query = "insert into board ("
                    + " no, userid, title, sdate, edate, location, content, ofile, sfile, cno) "
                    + " values ("
                    + " seq_board.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psmt = con.prepareStatement(query);
            psmt.setString(1, bdto.getUserid()); // 작성자id
            psmt.setString(2, bdto.getTitle()); // 글제목
            psmt.setDate(3, new java.sql.Date(bdto.getSdate().getTime())); // 시작일
            psmt.setDate(4, new java.sql.Date(bdto.getEdate().getTime())); // 종료일
            psmt.setString(5, bdto.getLocation()); // 지역
            psmt.setString(6, bdto.getContent()); // 내용
            psmt.setString(7, bdto.getOfile()); // original file name
            psmt.setString(8, bdto.getSfile()); // saved file name
            psmt.setInt(9, bdto.getCno()); // 카테고리번호
            result = psmt.executeUpdate(); // executeUpdate()의 리턴값은 영향을 받은 행의 수
        } catch (Exception e) {
            System.out.println("게시물 입력 중 예외 발생");
            e.printStackTrace();
        }
        return result; // 영향을 받은 행의 수 리턴
    }

    // R
    public PopDayBoardDTO selectView(String no) {
        PopDayBoardDTO bdto = new PopDayBoardDTO();
        String query = "select * from board where no = ?"; // no로 검색
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, no);
            rs = psmt.executeQuery();

            if (rs.next()) { // select한 결과를 DTO 객체에 저장
                bdto.setNo(rs.getString(1)); // 글번호
                bdto.setUserid(rs.getString(2)); // 작성자id
                bdto.setTitle(rs.getString(3)); // 글제목
                bdto.setSdate(rs.getDate(4)); // 시작일
                bdto.setEdate(rs.getDate(5)); // 종료일
                bdto.setLocation(rs.getString(6)); // 지역
                bdto.setContent(rs.getString(7)); // 내용
                bdto.setPostdate(rs.getDate(8)); // 작성일
                bdto.setOfile(rs.getString(9)); // original file name
                bdto.setSfile(rs.getString(10)); // saved file name
                bdto.setCno(rs.getInt(11)); // 카테고리번호
            }
        } catch (Exception e) {
            System.out.println("게시물 상세보기 중 예외 발생");
            e.printStackTrace();
        }
        return bdto;
    }

    // U
    public int updatePost(PopDayBoardDTO bdto) {
        int result = 0;
        try {
            String query = "update board"
                    + " set title = ?, sdate = ?, edate = ?, location = ?, content = ?, ofile = ?, sfile = ?, cno = ?"
                    + " where no = ?";

            psmt = con.prepareStatement(query);
            psmt.setString(1, bdto.getTitle()); // 글제목
            psmt.setDate(2, new java.sql.Date(bdto.getSdate().getTime())); // 시작일
            psmt.setDate(3, new java.sql.Date(bdto.getEdate().getTime())); // 종료일
            psmt.setString(4, bdto.getLocation()); // 지역
            psmt.setString(5, bdto.getContent()); // 내용
            psmt.setString(6, bdto.getOfile()); // original file name
            psmt.setString(7, bdto.getSfile()); // saved file name
            psmt.setInt(8, bdto.getCno()); // 카테고리번호
            psmt.setString(9, bdto.getNo()); // 글번호

            result = psmt.executeUpdate(); // 영향을 받은 행의 수 저장
        } catch (Exception e) {
            System.out.println("게시물 수정 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }

    // D
    public int deletePost(String no) {
        int result = 0;
        try {
            String query = "delete from board where no = ?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, no);
            result = psmt.executeUpdate(); // 영향을 받은 행의 수 저장
        } catch (Exception e) {
            System.out.println("게시물 삭제 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }
}

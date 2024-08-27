package model2.mvcPopDay.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBConnPool;
import model2.mvcPopDay.dto.PopDayCategoryDTO;

public class PopDayCategoryDAO extends DBConnPool {
	// DB 연결
	public PopDayCategoryDAO() { // public DBConnPool() 호출
	}
	
	// 카테고리 리스트 가져오기
    public List<PopDayCategoryDTO> getCategoryList() {
        List<PopDayCategoryDTO> categoryList = new ArrayList<>();
        String query = "select * from category"; // 예시 쿼리, 실제 테이블 명과 컬럼명에 맞게 수정 필요
        try {
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();
            while (rs.next()) {
                PopDayCategoryDTO cdto = new PopDayCategoryDTO();
                cdto.setCno(rs.getString("cno"));
                cdto.setCategory(rs.getString("category"));
                categoryList.add(cdto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return categoryList;
    }
		
}

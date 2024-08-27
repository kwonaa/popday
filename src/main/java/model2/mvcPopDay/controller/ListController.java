package model2.mvcPopDay.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.mvcPopDay.dao.PopDayBoardDAO;
import model2.mvcPopDay.dao.PopDayCategoryDAO;
import model2.mvcPopDay.dto.PopDayBoardDTO;
import model2.mvcPopDay.dto.PopDayCategoryDTO;
import utils.PopDayPage;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB Connection Test
		PopDayBoardDAO bdao = new PopDayBoardDAO();
		PopDayCategoryDAO cdao = new PopDayCategoryDAO();
//		PopDayMemberDAO mdao = new PopDayMemberDAO(); // Singleton Patten으로 연결
		
		// Map 생성
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 검색필드, 검색어 map에 저장
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		String cno = request.getParameter("cno"); // 추가된 부분
		
		System.out.println(searchField);
		System.out.println(searchWord);
		System.out.println(cno);
		
	    if (searchWord != null) { // 검색어가 있을 경우
	        map.put("searchField", searchField);
	        map.put("searchWord", searchWord);
	    } else {
	    	
	    }
	    
//	    if (cno != null) { // 카테고리번호가 있을 경우
//	        map.put("cno", cno);
//	    } else {
//	    	
//	    }
	    
	    if (cno != null && !cno.isEmpty()) { // cno가 null이 아니고 비어있지 않은 경우
	        map.put("cno", cno); // cno 파라미터를 Map에 추가
	    }
	    
	    
		
	    
		// 전체 글의 수
		int totalCount = bdao.selectCount(map);
		// 페이지당 글의 수
		int pageSize = 6;
		// 페이지 블록의 크기
		int blockPage = 5;
		
		// 페이지 번호 구하기
		int pageNum = 1; // 기본값 1 페이지
		String pageTemp = request.getParameter("pageNum");
		if(pageTemp != null && !pageTemp.equals("")) { // pageTemp값이 있으면
			pageNum = Integer.parseInt(pageTemp);
		}
		
		
		int start = (pageNum - 1) * pageSize + 1; // 첫 게시물 번호
		int end = pageNum * pageSize; // 마지막 게시물 번호
		map.put("start", start);
		map.put("end", end);
		
		List<PopDayBoardDTO> popupList = bdao.selectListPage(map); // 목록을 구해서 list에 저장
		List<PopDayCategoryDTO> categoryList = cdao.getCategoryList(); // 추가된 부분
		
		// DB 연결 닫기
		bdao.close(); 
		cdao.close();
//		mdao.close(); // Singleton Patten으로 연결
		
		
		// 페이지 블록 생성
		String pagingImg = PopDayPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/list.do", searchField, searchWord);
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		
		// 목록을 jsp 페이지로 전달
		request.setAttribute("popupList", popupList); // 속성명 변경
		request.setAttribute("map", map);
		request.setAttribute("categoryList", categoryList); // 추가된 부분
		request.getRequestDispatcher("/List.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

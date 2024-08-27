package model2.mvcPopDay.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.mvcPopDay.dao.PopDayBoardDAO;
import model2.mvcPopDay.dto.PopDayBoardDTO;

/**
 * Servlet implementation class ViewController
 */
@WebServlet("/view.do")
public class ViewController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Object bdto;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 게시물 불러오기
        PopDayBoardDAO bdao = new PopDayBoardDAO();
        String no = request.getParameter("no"); // 글번호
        String searchField = request.getParameter("searchField");
        String searchWord = request.getParameter("searchWord");
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("searchField", searchField);
        map.put("searchWord", searchWord);
        
        PopDayBoardDTO bdto = bdao.selectView(no); // 상세정보
        bdao.close();
        
        request.setAttribute("map", map);
        request.setAttribute("dto", bdto); // attribute name "dto"에 bdto를 저장
        request.getRequestDispatcher("/View.jsp").forward(request, response); // 포워딩. 주소 변경 안 됨
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}

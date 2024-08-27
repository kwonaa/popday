package model2.mvcPopDay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model2.mvcPopDay.dao.PopDayMemberDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/Login.jsp"; //로그인페이지
		HttpSession session=request.getSession();
		if(session.getAttribute("loginUser")!=null) { //로그인을 했으면 
			url="Main.jsp"; //메인페이지로
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/Login.jsp";
		String userid=request.getParameter("userid");		
		String pwd=request.getParameter("pwd");
		PopDayMemberDAO mdao=PopDayMemberDAO.getInstance(); //dao 인스턴스 사용
		int result=mdao.userCheck(userid, pwd);
		
		

		HttpSession session = request.getSession();
		session.setAttribute("userid", userid);
		if(result==1) {
			String nickname = mdao.getNickname(userid);
			boolean adminCheck = mdao.adminCheck(userid);
			session.setAttribute("loginUser", nickname); //"loginUser" attribute에 nickname저장		
			session.setAttribute("adminCheck", adminCheck); //attribute에 관리자 여부 저장
			
			//main.do로 주소변경 권장해서 MainServlet에서 실행하게 만듦
			url="Main.jsp";
			response.sendRedirect("/main.do?userid="+userid);
			return;
		} else if (result==0) {
			request.setAttribute("message", "비밀번호가 일치하지 않습니다.");
		} else if (result==-1) {
			request.setAttribute("message", "ID가 존재하지 않습니다.");
		}
		request.setAttribute("userid", userid);
		request.setAttribute("messageVisible", true);

		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
				
	}

}
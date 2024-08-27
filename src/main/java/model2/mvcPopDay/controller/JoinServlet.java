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
import model2.mvcPopDay.dto.PopDayMemberDTO;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("member/Join.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//폼에 입력한 한글깨짐방지
		request.setCharacterEncoding("UTF-8");
		
		String name=request.getParameter("name");
		String userid=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String nickname=request.getParameter("nickname");				
		
		PopDayMemberDTO mdto=new PopDayMemberDTO();
		mdto.setName(name);
		mdto.setUserid(userid);
		mdto.setPwd(pwd);
		mdto.setEmail(email);
		mdto.setPhone(phone);
		mdto.setNickname(nickname);		
		
		//관리자 여부 설정(아이디 admin을 가입하면 자동으로 관리자로 승격)
		if("admin".equals(userid)) {
			mdto.setAdmin(1); //관리자 아이디 이면 admin필드를 1로 설정
		}else {
			mdto.setAdmin(0); 
		}
		
		PopDayMemberDAO mdao=PopDayMemberDAO.getInstance();
		int result=mdao.insertMember(mdto);// insert실행
		if(result==1) {
			HttpSession session=request.getSession();
			session.setAttribute("userid", mdto.getUserid());
			request.setAttribute("message", "회원가입에 성공했습니다.");
		}else {
			request.setAttribute("message", "회원가입에 실패했습니다.");
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("member/Login.jsp");
		dispatcher.forward(request, response);
	}

}

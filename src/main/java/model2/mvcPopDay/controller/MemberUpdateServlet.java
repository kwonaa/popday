package model2.mvcPopDay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.mvcPopDay.dao.PopDayMemberDAO;
import model2.mvcPopDay.dto.PopDayMemberDTO;

@WebServlet({"/memberUpdate.do"})
public class MemberUpdateServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MemberUpdateServlet() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userid = request.getParameter("userid");
      PopDayMemberDAO mdao = PopDayMemberDAO.getInstance();
      PopDayMemberDTO mdto = mdao.getMember(userid);
      request.setAttribute("mdto", mdto);
      RequestDispatcher dispatcher = request.getRequestDispatcher("member/MemberUpdate.jsp");
      dispatcher.forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      String name = request.getParameter("name");
      String userid = request.getParameter("userid");
      String pwd = request.getParameter("pwd");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      String nickname = request.getParameter("nickname");
      
      PopDayMemberDTO mdto = new PopDayMemberDTO();
      mdto.setName(name);
      mdto.setUserid(userid);
      mdto.setPwd(pwd);
      mdto.setEmail(email);
      mdto.setPhone(phone);
      mdto.setNickname(nickname);      
      PopDayMemberDAO mdao = PopDayMemberDAO.getInstance();
      mdao.updateMember(mdto);
      response.sendRedirect("/memberUpdate.do?userid="+userid);
   }
}

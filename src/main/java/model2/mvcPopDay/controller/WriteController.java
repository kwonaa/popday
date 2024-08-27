package model2.mvcPopDay.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import model2.mvcPopDay.dao.PopDayBoardDAO;
import model2.mvcPopDay.dto.PopDayBoardDTO;
import utils.JSFunction;

/**
 * Servlet implementation class WriteController
 */
@WebServlet("/write.do")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Write.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폼에 입력한 한글 인코딩 처리. 깨짐 방지.
		request.setCharacterEncoding("UTF-8");
		
		// 파일 업로드 처리
		String saveDirectory = request.getServletContext().getRealPath("/Images"); // webapp 안에 Images 폴더 만들어야 함
		int maxPostSize = 10 * 1024 * 1024; // 첨부파일 최대 크기 10MB로 지정
		MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize); // 파일 업로드
		if(mr == null){
			JSFunction.alertLocation(response, "오류가 발생하였습니다.", "/write.do");
			return;
		}
				
		
        // 폼에 입력한 값을 dto에 저장
        PopDayBoardDTO bdto = new PopDayBoardDTO();
        bdto.setUserid(mr.getParameter("userid"));
        bdto.setTitle(mr.getParameter("title"));
        
        // 날짜 문자열을 java.sql.Date로 변환하여 DTO에 설정
        try {
            String sdateStr = mr.getParameter("sdate");
            String edateStr = mr.getParameter("edate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date sdate = sdf.parse(sdateStr);
            Date edate = sdf.parse(edateStr);
            bdto.setSdate(new java.sql.Date(sdate.getTime()));
            bdto.setEdate(new java.sql.Date(edate.getTime()));
        } catch (Exception e) {
            JSFunction.alertLocation(response, "날짜 형식이 올바르지 않습니다.", "/write.do");
            return;
        }

        bdto.setLocation(mr.getParameter("location"));
        bdto.setContent(mr.getParameter("content"));
        bdto.setCno(Integer.parseInt(mr.getParameter("cno"))); // 카테고리번호는 정수형
		
		// 업로드된 원본 파일명을 변경해서 중복 방지
		String fileName = mr.getFilesystemName("ofile");
		if(fileName != null) { // 첨부 파일이 있을 경우 파일명 변경 // 새로운 파일명 생성
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf(".")); // 확장자 구하기
			String newFileName = now + ext; // 새로운 파일명 만들기
			
			// 파일명 변경
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile); // 파일명 변경
			
			bdto.setOfile(fileName); // 원래 파일명
			bdto.setSfile(newFileName); // 새 파일명
		}
		
		
		
		// DAO 생성
		PopDayBoardDAO bdao = new PopDayBoardDAO();
		int result = bdao.insertWrite(bdto); // dto를 parameter값으로 전달
		bdao.close(); // close 작업
		
		// 성공 or 실패
		if(result == 1) { // 글쓰기 성공
			response.sendRedirect("/list.do"); // 목록으로
		} else { // 글쓰기 실패
			response.sendRedirect("/write.do"); // 등록으로
		}
	}

}
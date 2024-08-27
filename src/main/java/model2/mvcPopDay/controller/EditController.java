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
 * Servlet implementation class EditController
 */
@WebServlet("/edit.do")
public class EditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // 업로드 파일 설정
    private static final String UPLOAD_DIRECTORY = "/Images";
    private static final int MAX_POST_SIZE = 10 * 1024 * 1024; // 10MB
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String no = request.getParameter("no"); // 글번호
        PopDayBoardDAO bdao = new PopDayBoardDAO(); // bdao 생성
        PopDayBoardDTO bdto = bdao.selectView(no);
        request.setAttribute("dto", bdto); // "bdto" attribute name으로 전달
        request.getRequestDispatcher("/Edit.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 한글 인코딩 처리
        request.setCharacterEncoding("UTF-8");

        // 파일 업로드 처리
        String saveDirectory = request.getServletContext().getRealPath(UPLOAD_DIRECTORY);
        MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, MAX_POST_SIZE);
        if (mr == null) {
            JSFunction.alertBack(response, "첨부파일이 제한 용량을 초과합니다.");
            return;
        }

        // 폼 데이터 및 기존 파일 정보 가져오기
        String no = mr.getParameter("no");
        String prevOfile = mr.getParameter("prevOfile");
        String prevSfile = mr.getParameter("prevSfile");
        String title = mr.getParameter("title");
        String sdateStr = mr.getParameter("sdate");
        String edateStr = mr.getParameter("edate");
        String location = mr.getParameter("location");
        String content = mr.getParameter("content");
        int cno = Integer.parseInt(mr.getParameter("cno"));

        // Date 형식 변환
        java.sql.Date sdate = java.sql.Date.valueOf(sdateStr);
        java.sql.Date edate = java.sql.Date.valueOf(edateStr);

        // DTO에 폼 데이터 저장
        PopDayBoardDTO bdto = new PopDayBoardDTO();
        bdto.setNo(no);
        bdto.setTitle(title);
        bdto.setSdate(sdate);
        bdto.setEdate(edate);
        bdto.setLocation(location);
        bdto.setContent(content);
        bdto.setCno(cno);

        // 파일 업로드 처리 및 기존 파일 삭제
        String fileName = mr.getFilesystemName("ofile");
        if (fileName != null) { // 새 파일이 업로드된 경우
            String newFileName = generateNewFileName(fileName);
            File oldFile = new File(saveDirectory + File.separator + fileName);
            File newFile = new File(saveDirectory + File.separator + newFileName);
            oldFile.renameTo(newFile);

            bdto.setOfile(fileName); // 원래 파일명
            bdto.setSfile(newFileName); // 새 파일명

            // 기존 파일 삭제
            FileUtil.deleteFile(request, UPLOAD_DIRECTORY, prevSfile);
        } else { // 새 파일이 업로드되지 않은 경우 기존 파일 정보 유지
            bdto.setOfile(prevOfile);
            bdto.setSfile(prevSfile);
        }

        // DB 업데이트 처리
        PopDayBoardDAO bdao = new PopDayBoardDAO();
        int result = bdao.updatePost(bdto);
        bdao.close();

        // 결과에 따라 페이지 이동 처리
        if (result == 1) {
            response.sendRedirect("/view.do?no=" + no);
        } else {
            JSFunction.alertLocation(response, "수정에 실패했습니다. 다시 시도하세요.", "/view.do?no=" + no);
        }
    }

    // 파일명 중복 방지를 위한 새 파일명 생성 메서드
    private String generateNewFileName(String fileName) {
        String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
        String ext = fileName.substring(fileName.lastIndexOf("."));
        return now + ext;
    }
}


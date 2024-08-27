package model2.mvcPopDay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.mvcPopDay.dao.PopDayBoardDAO;
import model2.mvcPopDay.dto.PopDayBoardDTO;
import utils.JSFunction;
import fileupload.FileUtil;

@WebServlet("/delete.do")
public class DeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doGet 메서드가 비어있으면 doPost로 리다이렉트
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String no = request.getParameter("no"); // 글번호    
            
            PopDayBoardDAO bdao = new PopDayBoardDAO(); // bdao 객체 생성
            PopDayBoardDTO bdto = bdao.selectView(no); // 상세정보 DTO에 저장

            int result = bdao.deletePost(no); // 삭제 처리
            
            if (result == 1) {
                // 첨부파일 삭제
                String saveFileName = bdto.getSfile(); // 저장된 파일명
                FileUtil.deleteFile(request, "/Images", saveFileName); // 파일 삭제
                
                // 삭제 성공 메시지 및 목록 페이지로 이동
                JSFunction.alertLocation(response, "삭제 완료됐습니다.", request.getContextPath() + "/list.do");
            } else {
                JSFunction.alertBack(response, "삭제에 실패했습니다.");
            }
            bdao.close();
        } catch (Exception e) {
            e.printStackTrace();
            JSFunction.alertBack(response, "오류가 발생했습니다.");
        }
    }
}

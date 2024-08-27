package model2.mvcPopDay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model2.mvcPopDay.dto.PopDayMemberDTO;

public class PopDayMemberDAO {
	//Singleton Pattern 적용---------------------------------------
	
	//private 생성자
	private PopDayMemberDAO() {		
	};
	//private static 인스턴스 생성
	private static PopDayMemberDAO instance=new PopDayMemberDAO();
	//private static 인스턴스를 리턴하는 함수
	public static PopDayMemberDAO getInstance() {
		return instance;
	}
	
	//------------------------------------------------------------

	//DBCP연결
	public Connection getConnection() throws Exception{
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		return conn;
	}
	//로그인처리
	public int userCheck(String userid,String pwd) {
		int result=-1;
		String sql="select pwd from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pwd")!=null && rs.getString("pwd").equals(pwd)) {
					result=1;//비밀번호일치하는 경우
				}else {
					result=0;//비밀번호일치하지 않는 경우
				}
			}else {
				result=-1; // id가 존재하지 않는 경우
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) { 
					rs.close();
				}
				if(pstmt!=null) { 
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	// 닉네임 반환
		public String getNickname(String userid) {
			String nickname = null;
			String sql = "select nickname FROM member WHERE userid=?";
			try (Connection conn = getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, userid);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						nickname = rs.getString("nickname");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return nickname;
		}
	
	//회원정보검색
	public PopDayMemberDTO getMember(String userid) {
		PopDayMemberDTO mdto=null;
		String sql="select * from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				mdto=new PopDayMemberDTO();
				mdto.setName(rs.getString("name"));
				mdto.setUserid(rs.getString("userid"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setNickname(rs.getString("nickname"));	
				mdto.setAdmin(rs.getInt("admin")); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) { 
					rs.close();
				}
				if(pstmt!=null) { 
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mdto;
	}
	//회원정보수정
	public int updateMember(PopDayMemberDTO mdto) {
		int result=-1;
		String sql="update member set pwd=?, email=?, phone=?, nickname=? where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mdto.getPwd());
			pstmt.setString(2, mdto.getEmail());
			pstmt.setString(3, mdto.getPhone());
			pstmt.setString(4, mdto.getNickname());			
			pstmt.setString(5, mdto.getUserid());
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {				
				if(pstmt!=null) { 
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;			
	}
	//id중복체크
	public int confirmID(String userid) {
		int result=-1;
		String sql="select userid from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=1; // id가 중복되는 경우
			}else {
				result=-1; // id가 중복되지 않는 경우
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) { 
					rs.close();
				}
				if(pstmt!=null) { 
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//닉네임 중복체크
		public int confirmNickname(String nickname) {
			int result=-1;
			String sql="select nickname from member where nickname=?";
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn=getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,nickname);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					result=1; // nickname가 중복되는 경우
				}else {
					result=-1; // nickname가 중복되지 않는 경우
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null) { 
						rs.close();
					}
					if(pstmt!=null) { 
						pstmt.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
	//회원등록
	public int insertMember(PopDayMemberDTO mdto) {
		int result=-1;
		String sql="insert into member(name, userid, pwd, email, phone, nickname) values(?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getUserid());
			pstmt.setString(3, mdto.getPwd());
			pstmt.setString(4, mdto.getEmail());
			pstmt.setString(5, mdto.getPhone());		
			pstmt.setString(6, mdto.getNickname());		
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {				
				if(pstmt!=null) { 
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;		
	}
	//관리자 아이디 체크
	public boolean adminCheck(String userid) {
		boolean adminCheck = false; //초기값 설정
		String sql = "select admin from member where userid = ?";
		try(Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, userid);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					int adminValue = rs.getInt("admin");
					if(adminValue == 1) {
						adminCheck = true; //관리자인 경우
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return adminCheck; //관리자인지 여부 반환
	}
}


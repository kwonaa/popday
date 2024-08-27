//id, pw 입력확인(로그인페이지)
function loginCheck(){
	if(document.frm.userid.value.length==0){
		alert("아이디를 입력하세요");
		frm.userid.focus();
		return false;
	}
	if(document.frm.pwd.value==""){
		alert("비밀번호를 입력하세요");
		frm.pwd.focus();
		return false;
	}
}
//id 중복체크(회원가입 페이지)
function idCheck(){
	if(document.frm.userid.value==""){
		alert("아이디를 입력하세요");
		frm.userid.focus();
		return;
	}
	var url="idCheck.do?userid="+document.frm.userid.value;
	//팝업창 오픈
	window.open(url,"_blank_1","width=450,height=200"); //"_blank_1"은 팝업창 이름
	
}
//부모창 userid에 값 넣기
function idok(userid){
	opener.frm.userid.value = document.frm.userid.value;
			opener.frm.reid.value = document.frm.userid.value; //"reid"는 중복확인이 완료된 후 userid를 변경했는지 확인용
		
		self.close(); //팝업창 닫기 window.close(); 도 가능		

}
//닉네임 중복체크
function nicknameCheck(){
	if(document.frm.nickname.value==""){
		alert("닉네임을 입력하세요");
		frm.nickname.focus();
		return;
	}
	var url="nicknameCheck.do?nickname="+document.frm.nickname.value;
	//팝업창 오픈
	window.open(url,"_blank_2","width=450,height=200"); //"_blank_2"은 팝업창 이름	
}
//부모창 nicknamename에 값 넣기
function nicknameok(nickname){
	opener.frm.nickname.value = document.frm.nickname.value;
	opener.frm.renickname.value = document.frm.nickname.value; 
		
		self.close(); //팝업창 닫기 window.close(); 도 가능	
}
		
	
	

//등록화면 입력값 validation
function joinCheck(){
	if(document.frm.name.value.length==0){
		alert("이름을 입력해주세요.")
		frm.name.focus();
		return false;
		}
	if(document.frm.userid.value.length==0){
		alert("아이디를 입력해주세요.")
		frm.userid.focus();
		return false;
		}
	if(document.frm.userid.value.length<3){
		alert("아이디를 3글자 이상으로 입력해주세요.")
		frm.userid.focus();
		return false;
		}
	if(document.frm.pwd.value==""){
		alert("비밀번호를 입력해주세요.")
		frm.pwd.focus();
		return false;
		}
	if(document.frm.pwd.value!=document.frm.pwd_check.value){
		alert("비밀번호가 일치하지 않습니다.")
		frm.pwd.focus();
		return false;
		}
	if(document.frm.nickname.value.length==0){
		alert("닉네임을 입력해주세요.")
		frm.nickname.focus();
		return false;
		}
		
		
		
		let email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
		if(!email_regex.test(document.frm.email.value)){
			alert("이메일이 올바른 형식이 아닙니다.");
			frm.email.focus();
			return false;
		}
		//아이디 중복체크여부 확인
	if(document.frm.reid.value!==document.frm.userid.value){
		alert("아이디 중복체크를 해주세요.")
		frm.userid.focus();
		return false;
		}
		//닉네임 중복체크여부 확인
	if(document.frm.renickname.value!==document.frm.nickname.value){
		alert("닉네임 중복체크를 해주세요.")
		frm.nickname.focus();
		return false;
		}
		
	
	/*
	//return true; => join.jsp => joinCheck로 보내는데 //안써도 됨
	
	*/
	}

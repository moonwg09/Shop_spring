<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/member/join.css">

<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>

<div class="wrapper">
	<form id="join_form" method="post">
	<div class="wrap">
			<div class="subjecet">
				<span>회원가입</span>
			</div>
			<div class="id_wrap">
				<div class="id_name">아이디</div>
				<div class="id_input_box">
					<input class="id_input" name="memberId">
				</div>
			</div>
			<div class="pw_wrap">
				<div class="pw_name">비밀번호</div>
				<div class="pw_input_box">
					<input type="password" class="pw_input" name="memberPw">
				</div>
			</div>
			<div class="pwck_wrap">
				<div class="pwck_name">비밀번호 확인</div>
				<div class="pwck_input_box">
					<input type="password" class="pwck_input">
				</div>
			</div>
			<div class="user_wrap">
				<div class="user_name">이름</div>
				<div class="user_input_box">
					<input class="name_input" name="memberName">
				</div>
			</div>
			<div class="mail_wrap">
				<div class="mail_name">이메일</div>
				<div class="mail_input_box">
					<input class="mail_input" name="memberMail">
				</div>
				
				<!-- TODO: 이메일 칸 정리하기(@을 넣을 건지?) -->
				<div class="mail_check_wrap">
					<div class="mail_check_input_box">
						<input class="mail_check_input">
					</div>
					<div class="mail_check_button">
						<span>인증번호 전송</span>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="address_wrap">
				<div class="address_name">주소</div>
				<div class="address_input_1_wrap">
					<div class="address_input_1_box">
						<input class="address_input_1" name="memberAddr1">
					</div>
					<div class="address_button">
						<span>주소 찾기</span>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class ="address_input_2_wrap">
					<div class="address_input_2_box">
						<input class="address_input_2" name="memberAddr2">
					</div>
				</div>
				<div class ="address_input_3_wrap">
					<div class="address_input_3_box">
						<input class="address_input_3" name="memberAddr3">
					</div>
				</div>
			</div>
			<div class="join_button_wrap">
				<input type="button" class="join_button" value="가입하기">
			</div>
		</div>
	</form>
</div>

<script>
	$(document).ready(function() {
		
		/* 엔터키로 회원가입 버튼 */
		$(".id_input, .pw_input, .pwck_input, .name_input, .mail_input, .address_input_1").keydown(function(key) {
	        if (key.keyCode == 13) {
	            key.preventDefault();
	            $(".join_button").click();
	        }
	    });
		
		/* 2. 가입하기 버튼 클릭 (기본 제출 처리) */
		$(".join_button").click(function() {
			
			var id = $(".id_input").val();
	        var pw = $(".pw_input").val();
	        var pwck = $(".pwck_input").val();
	        var name = $(".name_input").val();
	        var mail = $(".mail_input").val();
	        var add1 = $(".address_input_1").val();
	        // TODO: 주소 칸 정리하기
	        
	        if(id == "" || pw == "" || name == "" || mail == "" || add1 == "") {
	            alert("필수 입력 항목을 모두 채워주세요.");
	            return false;
	        }
	        
	        if(pw !== pwck) {
	            alert("비밀번호가 일치하지 않습니다.");
	            $(".pwck_input").focus();
	            return false; // 서브밋 중단
	        }
	        
	        $.ajax({
	        	type : "post",
	        	url : "/member/memberIdChk", // Q. 얘랑 mapper의 id랑 일치해야 하나?
	        	data : function(result) {
	        		if(result != 'fail') {
	        			$("#join_form").attr("action", "/member/join");
	        			$("#join_form").submit();
	        		} else {
	        			alert("중복된 아이디가 존재합니다. 다른 아이디를 입력해 주세요.");
	        			$(".id_input").focus();
	                    return false;
	        		}
	        	}
	        }) 
		});
		
		/* 주소록 API 팝업창 연동 */
		$("#address_btn").click(function() {
			new daum.Postcode({
		        oncomplete: function(data) {
		        	$(".address_input_1").val(data.zonecode); // 우편번호 넣기
		            $(".address_input_2").val(data.userSelectedType === 'R' ? data.roadAddress : data.jibunAddress); // 도로명 또는 지본 주소 넣기
		            $(".address_input_3").focus(); // 상세주소창으로 포커스 이동
		        }
		    }).open();
		});
		
	});
</script>

</body>
</html>
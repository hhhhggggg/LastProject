<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="../includes/header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title" style="text-align: center;">회원가입</h3>
				</div>
				<div class="panel-body">
					<form action="/join/register" method="post"
						onsubmit="return checkValidate();">
						<fieldset>
							<div class="form-group">
								<label class="radio-inline"> <input type="radio"
									name="checked" value="0" checked onchange="userType()">
									개인
								</label> <label class="radio-inline"> <input type="radio"
									name="checked" value="1" onchange="userType()"> 사업자
								</label>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="아이디" name="id" id="id"
									type="text" autofocus>
								<div id="idError" style="color: red;"></div>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="비밀번호" name="pw" id="pw"
									type="password" value="">
								<div id="pwError" style="color: red;"></div>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="이름" name="name"
									id="name" type="text">
								<div id="nameError" style="color: red;"></div>
							</div>
							<div class="form-group">
								<input class="form-control"
									placeholder="전화번호 (ex: 010-0000-0000)" name="phone" id="phone"
									type="tel" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required>
								<div id="phoneError" style="color: red;"></div>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="이메일" name="email"
									id="email" type="email">
								<div id="emailError" style="color: red;"></div>
							</div>
							<div id="attachArea"
								style="display: none; width: 150px; height: 100px; border: 1px solid; text-align: center;">
								첨부파일 공간 대충 느낌만</div>
							<!-- Change this to a button or input when using this as a form -->
							<button type="submit" class="btn btn-lg btn-success btn-block">가입하기</button>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<c:if test="${msg eq '중복된 아이디입니다.'}">
    <script>
        alert('${msg}');
    </script>
</c:if>
<script>
	// 회원 가입 폼 체크하는 함수
	function checkValidate() {
		var id = document.getElementById('id').value;
		var pw = document.getElementById('pw').value;
		var name = document.getElementById('name').value;
		var phone = document.getElementById('phone').value;
		var email = document.getElementById('email').value;

		var idError = document.getElementById('idError');
		var pwError = document.getElementById('pwError');
		var nameError = document.getElementById('nameError');
		var phoneError = document.getElementById('phoneError');
		var emailError = document.getElementById('emailError');

		// 아이디 유효성 검사
		if (id === '') {
			idError.textContent = '아이디를 입력하세요.';
		} else {
			idError.textContent = '';
		}

		// 비밀번호 유효성 검사
		if (pw === '') {
			pwError.textContent = '비밀번호를 입력하세요.';
		} else {
			pwError.textContent = '';
		}

		// 이름 유효성 검사
		if (name === '') {
			nameError.textContent = '이름을 입력하세요.';
		} else {
			nameError.textContent = '';
		}

		// 전화번호 유효성 검사 (정규 표현식 사용)
		var phoneCheck = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
		if (!phoneCheck.test(phone)) {
			phoneError.textContent = '전화번호를 올바른 형식(ex: 010-0000-0000)으로 입력하세요.';
		} else {
			phoneError.textContent = '';
		}

		// 이메일 유효성 검사
		var emailCheck = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		if (!emailCheck.test(email)) {
			emailError.textContent = '이메일을 올바른 형식(ex: example@example.com 으로 입력하세요.';
		} else {
			emailError.textContent = '';
		}

		// 공백체크 및 메시지 출력
		if (id === '' || pw === '' || name === '' || !phoneCheck.test(phone)
				|| !emailCheck.test(email)) {
			return false;
		}
		return true;
	}

	//사업자만 첨부파일을 넣을 수 있으니까
	function userType() {
		var checkUser = document.querySelector('input[name="checked"]:checked').value;
		var attachArea = document.getElementById('attachArea');

		if (checkUser === '0') {
			// 나중에 첨부파일을 넣을 공간을 숨김
			attachArea.style.display = 'none';
		} else if (checkUser === '1') {
			// 나중에 첨부파일을 넣을 공간을 표시
			attachArea.style.display = 'block';
		}
	}
</script>

<%@include file="../includes/footer.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title" style="text-align: center;">로그인</h3>
				</div>
				<div class="panel-body">
					<form action="/join/login" method="post">
						<fieldset>
							<div class="form-group">
								<label class="radio-inline"> <input type="radio"
									name="checked" value="0" checked onchange="userType()">
									개인
								</label> <label class="radio-inline"> <input type="radio"
									name="checked" value="1"> 사업자
								</label>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="아이디" name="id"
									type="text" autofocus>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="비밀번호" name="pw"
									type="password" value="">
							</div>
							<!-- Change this to a button or input when using this as a form -->
							<button type="submit" class="btn btn-lg btn-success btn-block">로그인</button>
                            <button id="registerButton" type="button" class="btn btn-lg btn-primary btn-block" onclick="location.href='/join/register'">회원가입</button>
                       </fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="../includes/footer.jsp"%>
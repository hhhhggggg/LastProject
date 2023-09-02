<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../includes/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <%-- 로그인을 안했을 때 --%>
                <c:if test="${not empty id}">
                    <div id="welcomeMessage">
                        <p><strong>${id}</strong>님 안녕하세요!</p>
                    </div>
                    <button id="logoutButton" class="btn btn-danger">로그아웃</button>
                    
                    <button id="indexButton">로그인</button>
                </c:if>

                <%-- 로그인을 했을 때 --%>
                <c:if test="${empty id}">
                    <button id="loginButton">로그인</button>
                    <button id="registerButton">회원가입</button>
                </c:if>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        // "로그인" 버튼 클릭 시 로그인 페이지로 이동
        $("#loginButton").click(function() {
            self.location = "/join/login";
        });

        // "회원가입" 버튼 클릭 시 회원가입 페이지로 이동
        $("#registerButton").click(function() {
            self.location = "/join/register";
        });

        // "회원가입" 버튼 클릭 시 회원가입 페이지로 이동
        $("#registerButton").click(function() {
            self.location = "/join/index2";
        });
        // "로그아웃" 버튼 클릭 시 로그아웃 처리
        $("#logoutButton").click(function() {
        	//로그인 세션 삭제
            });
        });
    });
</script>

<%@ include file="../includes/footer.jsp" %>

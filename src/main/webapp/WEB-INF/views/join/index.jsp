<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../includes/header.jsp" %>

<h2>로그인 정보</h2>
<p>name : ${user.name }</p>
<p>id : ${user.id }</p>
<p>pw : ${user.pw }</p>
<p>email : ${user.email }</p>
<p>phone : ${user.phone }</p>



<form action="/join/logout" method="post">
	<button type="submit">logout</button>
</form>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var result = '<c:out value="${result}"/>';

        checkAlert(result);

        history.replaceState({}, null, null);

        function checkAlert(result) {
            if (result === '' || history.state) {
                return;
            }
            alert(result);
        }
    });
</script>
<%@ include file="../includes/footer.jsp" %>

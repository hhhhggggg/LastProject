<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기 결과</title>
</head>
<body>
    <h1>아이디 찾기 결과</h1>
    
    <p>
        <%-- 결과 메시지를 표시합니다. --%>
        <c:out value="${message}" />
    </p>
    
    <%-- 아이디를 표시합니다. --%>
    <c:if test="${foundId != null}">
        <p>찾은 아이디: <c:out value="${foundId}" /></p>
    </c:if>
    
    <p><a href="/join/id_find">다시 찾기</a></p>
</body>
</html>
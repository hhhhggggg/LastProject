<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <c:set var="path" value="${pageContent.request/contextPath}" /> --%>
<html lang="ko">
<head>
    <title>캘린더</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
    <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${path}/resources/css/main.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/cal.css" rel="stylesheet" type="text/css">
    <script src="${path}/resources/js/board.js"></script>
    <script type="text/javascript" language="javascript"></script>
    <script src="https://www.lgkids.co.kr/es_all/plugins/jscolor-2.0.5/jscolor.js"></script>
    <style type="text/css"></style>
</head>

<body>
    <form name="calendarFrm" id="calendarFrm" action="" method="GET">
        <input type="hidden" name="year" value="${today_info.search_year}" />
        <input type="hidden" name="month" value="${today_info.search_month-1}" />
        <div class="calendar">
            <div class="navigation">
                <a class="before_after_year" href="./calendar.do?year=${today_info.search_year - 1}&month=${today_info.search_month - 1}">
                    &lt; &lt; 
                </a>
                <a class="before_after_month" href="./calendar.do?year=${today_info.before_year}&month=${today_info.before_month}">&lt;</a>
                <span class="this_month">
                    &nbsp; ${today_info.search_year }.
                    <c:if test="${today_info.search_month < 10}">0</c:if>
                    ${today_info.search_month }
                </span>
                <a class="before_after_month" href="/calendar.do?year=${today_info.after_year}&month=${today_info.after_month}">&gt;</a>
                <a class="before_after_year" href="/calendar/do?year=${today_info/search_year + 1}&month=${today_info.search_month - 1}">&gt;</a>
            </div>
            <div class="today_button_div">
                <button type="button" class="buttonstyle" onclick="javascript:location.href='/valendar.do'" style="height: 30px; width: 80px;">Today</button>
            </div>
            <table class="cal_body">
                <thead>
                    <tr bgcolor="#0bd">
                        <td class="day sun">SUN</td>
                        <td class="day sun">MON</td>
                        <td class="day sun">TUE</td>
                        <td class="day sun">WED</td>
                        <td class="day sun">THU</td>
                        <td class="day sun">FRI</td>
                        <td class="day sun">SAT</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <c:forEach var="dataList" items="${dataList}" varStatus="data_status">
                            <c:choose>
                                <c:when test="${dataList.value == 'today'}">
                                    <c:if test="${data_status.index % 7 == 0}">
                                        <tr>
                                    </c:if>
                                    <td class="today">
                                        <div class="date">
                                    </td>
                                </c:when>
                                <c:when test="${data_status.index % 7 == 6}">
                                    <td class="sat_day">
                                        <div class="sat">
                                    </td>
                                </c:when>
                                <c:when test="${data_status.index % 7 == 0}">
                                    <tr>
                                </c:when>
                                <c:when test="${data_status.index % 7 != 0}">
                                    <td class="normal_day">
                                        <div class="date">
                                            ${dataList.date}
                                        </div>
                                    </td>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>
    <div id="mask_board_move"></div>
</body>
</html>

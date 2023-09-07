<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
<head>
<title>캘린더</title>
<link href="/resources/assets/css/cal.css" rel="stylesheet" type="text/css">

</head>
<body>
	<form name="calendarFrm" id="calendarFrm" action="" method="GET">

		<div class="calendar">

			<!--날짜 네비게이션  -->
			<div class="navigation">
				<a class="before_after_year"
					href="./calendar.do?year=${today_info.search_year-1}&month=${today_info.search_month-1}">
					&lt;&lt; <!-- 이전년도 -->
				</a> <a class="before_after_month"
					href="./calendar.do?year=${today_info.before_year}&month=${today_info.before_month}">
					&lt; <!-- 이전 달 -->
				</a> <span class="this_month"> &nbsp;${today_info.search_year}. <c:if
						test="${today_info.search_month<10}">0</c:if>${today_info.search_month}
				</span> 
				<a class="before_after_month"
					href="/calendar?year=${today_info.after_year}&month=${today_info.after_month}">
					<!-- 다음달 --> &gt;
				</a> <a class="before_after_year"
					href="/calendar?year=${today_info.search_year+1}&month=${today_info.search_month-1}">
					<!-- 다음해 --> &gt;&gt;
				</a>
			</div>

			<div class="today_button_div">
			<input type="button" class="today_button" onclick="#" value="go today"/>
			</div>
			<table class="calendar_body">

				<thead>
					<tr bgcolor="#CECECE">
						<td class="day sun">일</td>
						<td class="day">월</td>
						<td class="day">화</td>
						<td class="day">수</td>
						<td class="day">목</td>
						<td class="day">금</td>
						<td class="day sat">토</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="dateList" items="${dateList}" varStatus="date_status">
							<c:choose>
								<c:when test="${dateList.value=='today'}">
									<td class="today">
										<div class="date">${dateList.date}</div>
<%-- 										<p>${ok}</p> --%>
<!-- 										<a class = "contents">그니까 여기에 이제 무언가를 쓰면 된다는 거지</a> -->
									</td>
								</c:when>
								<c:when test="${date_status.index%7==6}">
									<td class="sat_day">
										<div class="sat">${dateList.date}</div>
										<div></div>
									</td>
								</c:when>
								<c:when test="${date_status.index%7==0}">
					</tr>
					<tr>
						<td class="sun_day">
							<div class="sun">${dateList.date}</div>
							<div></div>
						</td>
						</c:when>
						<c:otherwise>
							<td class="normal_day">
								<div class="date">${dateList.date}</div>
								<div></div>
							</td>
						</c:otherwise>
							</c:choose>
						</c:forEach>
				</tbody>

			</table>
		</div>
	</form>
</body>
</html>

<script type="text/javascript">
	 $(function(){
		//기본모달창
		$('#contents').click(function(e){
	
		  e.preventDefault();
		  wrapCreateBoardByMask();
	});

	//닫기 버튼 눌렀을 때
	$('.normal_move_board_modal .top .close').click(function(e){
	  //링크 기본동작 작동하지 않게 함
	  e.preventDefault();
	  $('#mask_board_move, .normal_move_board_model').hide();
	});

	$('#mask_board_move').click(function(){
	  $(this).hide();
	  $('normal_move_board_modal').hide();
	});

</script>

<%@include file="../includes/footer.jsp"%>

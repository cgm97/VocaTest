<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="EUC-KR">
	<title>모의 토익 VOCA 단어 시험 - 회차</title>
	</head>
<body>
	<h1>모의 토익 VOCA 단어 시험 - 결과모음</h1>
	<myTags:login_username />
	<c:forEach var="list" items="${result_list}" varStatus="i">
		<b>${i.count}회차</b> 점수는 <b>${list.getScore()}점</b> 입니다.<br><br>
	</c:forEach>
</body>
</html>
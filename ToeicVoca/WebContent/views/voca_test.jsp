<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토익 테스트 웹 어플리케이션 </title>
</head>
<body>
	<h1>모의 토익 VOCA 단어 시험</h1>
	 <myTags:login_username />
	 <jsp:include page="/question/${check}"></jsp:include>
</body>
</html>


				
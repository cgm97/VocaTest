<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토익 테스트 - 결과</title>
</head>
<body>
	<h1>모의 토익 VOCA 단어 시험 - 결과</h1>
	<myTags:login_username />
	<form action="${pageContext.request.contextPath}/vocamanage?action=save_result" method="post">
		<div>
			<!-- 문제별 정답 체크 -->
			<h2>각 문제별 정답 체크</h2>
			<c:forEach var="score" items="${check_matter}" >
				<p><c:out value="${score}" /></p>
			</c:forEach>
		</div>
	
		<div>
			<!-- 점수 -->
			<h2>나의 점수</h2>
			<p>${check_score}점 / ${fn:length(check_matter)}점</p>
		</div>
		<input type="submit" value="성적 저장" />
	</form>
</body>
</html>


				
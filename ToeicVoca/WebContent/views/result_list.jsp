<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="EUC-KR">
	<title>���� ���� VOCA �ܾ� ���� - ȸ��</title>
	</head>
<body>
	<h1>���� ���� VOCA �ܾ� ���� - �������</h1>
	<myTags:login_username />
	<c:forEach var="list" items="${result_list}" varStatus="i">
		<b>${i.count}ȸ��</b> ������ <b>${list.getScore()}��</b> �Դϴ�.<br><br>
	</c:forEach>
</body>
</html>
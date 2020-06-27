<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토익 테스트 - 테스트</title>
</head>
<body>
<script>
	function noEvent() {// 새로 고침 방지
            if (event.keyCode == 116) {
                alert("새로고침을 할 수 없습니다.");
                event.keyCode = 2;
                return false;
            } else if (event.ctrlKey&& (event.keyCode == 78 || event.keyCode == 82)) {
                return false;
            }
        }
    document.onkeydown = noEvent;
</script>
	<h1>모의 토익 VOCA 단어 시험</h1>
	${check_matter}
	 <myTags:login_username />
	 <jsp:include page="/question/${check}"></jsp:include>
</body>
</html>


				
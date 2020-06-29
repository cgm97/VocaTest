<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<p>
	${username}님... 환영합니다!
	<button onclick="location.href='${pageContext.request.contextPath}/vocamanage?action=logout'">로그아웃</button>
</p>

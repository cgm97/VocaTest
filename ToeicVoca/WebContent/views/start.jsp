<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토익 테스트 - 로그인</title>
</head>
<body>
	<div>
        <form action="${pageContext.request.contextPath}/vocamanage?action=voca_info" method="post">
            <div>
                <h2>로그인</h2>
                <input name="id" type="text" placeholder="ID" required />
                <input name="pw" type="password" placeholder="Password" required />
                <input type="submit" value="로그인" />
                
            </div>
        </form>
        <p>회원가입 하러가기 <a href='${pageContext.request.contextPath}/vocamanage?action=insert'>Click Here!</a></p>
	</div>
</body>
</html>
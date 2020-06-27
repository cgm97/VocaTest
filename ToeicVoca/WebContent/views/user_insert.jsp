<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토익 테스트 - 회원가입</title>
</head>
<body>
	<div>
        <form action="${pageContext.request.contextPath}/vocamanage?action=insert_process" method="post">
            <div>
                <h2>회원가입</h2>
          		  아이디<br><input name="id" type="text" placeholder="ID" required /><br>
          		  비밀번호<br><input name="pw" type="password" placeholder="Password" required /><br>
          		  이름<br><input name="name" type="text" placeholder="name" required /><br>
                <input type="submit" value="회원가입" />               
            </div>
        </form>
	</div>
</body>
</html>
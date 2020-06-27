<%@page import="com.wp.toeic.ToeicDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.wp.toeic.*"%>
	<article>
		<form action="${pageContext.request.contextPath}/vocamanage?action=voca_test2" method="post">					
		<div class="question">
			<p><b>${matter1.getNum()}. ${matter1.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q1" value="1" required/>(1). 재개발 프로젝트 <br>
			<input type="radio" name="q1" value="2"/>(2). 새로운 프로젝트<br>
			</div>
		</div>	
		<div class="question">
			<p><b>${matter2.getNum()}. ${matter2.getMatter()}</b></p>
			<div>
			<input type="radio" name="q2" value="1" required />(1). 동)임명하다, 지명하다<br>
		 	<input type="radio" name="q2" value="2"/>(2). 동)실망시키다. <br>

			</div>
		</div>
		<div class="question">
			<p><b>${matter3.getNum()}. ${matter3.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q3" value="2" required/>(1). B에게 A를 제공하다 <br>
			<input type="radio" name="q3" value="1"/>(2). A에게 B를 제공하다<br>
			</div>
		</div>	
		<div style="padding-top:50px; text-align: left;">
			<input type="button" value="<< 이전 " onclick="window.history.go(-1);"/>
			<input type="submit" value="다음  >>" />
		</div>
		</form>
	</article>
	
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
 			<input type="radio" name="q1" value="1" required/>(1). �簳�� ������Ʈ <br>
			<input type="radio" name="q1" value="2"/>(2). ���ο� ������Ʈ<br>
			</div>
		</div>	
		<div class="question">
			<p><b>${matter2.getNum()}. ${matter2.getMatter()}</b></p>
			<div>
			<input type="radio" name="q2" value="1" required />(1). ��)�Ӹ��ϴ�, �����ϴ�<br>
		 	<input type="radio" name="q2" value="2"/>(2). ��)�Ǹ���Ű��. <br>

			</div>
		</div>
		<div class="question">
			<p><b>${matter3.getNum()}. ${matter3.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q3" value="2" required/>(1). B���� A�� �����ϴ� <br>
			<input type="radio" name="q3" value="1"/>(2). A���� B�� �����ϴ�<br>
			</div>
		</div>	
		<div style="padding-top:50px; text-align: left;">
			<input type="button" value="<< ���� " onclick="window.history.go(-1);"/>
			<input type="submit" value="����  >>" />
		</div>
		</form>
	</article>
	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
	<article>
		<form action="${pageContext.request.contextPath}/vocamanage?action=voca_test4" method="post">
		<div class="question">
			<p><b>${matter7.getNum()}. ${matter7.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q7" value="1" required/>(1). ��) ����ϴ� <br>
			<input type="radio" name="q7" value="2"/>(2). ��)����ϴ�<br>
			</div>
		</div>	
		<div class="question">
			<p><b>${matter8.getNum()}. ${matter8.getMatter()}</b></p>
			<div>
			<input type="radio" name="q8" value="1" required />(1). ��) ����, ����ϴ�<br>
		 	<input type="radio" name="q8" value="2"/>(2). ��)�����ִ�, ������ <br>

			</div>
		</div>
		<div class="question">
			<p><b>${matter9.getNum()}. ${matter9.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q9" value="1" required/>(1). ��) �и���, ��Ȯ�� <br>
			<input type="radio" name="q9" value="2"/>(2). ��) �������ִ�<br>
			</div>
		</div>	
		<div style="padding-top:50px; text-align: left;">
			<input type="button" value="<< ���� " onclick="window.history.go(-1);"/>
			<input type="submit" value="����  >>" />
		</div>
		</form>
	</article>
	
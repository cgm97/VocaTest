<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
	<article>
		<form action="${pageContext.request.contextPath}/vocamanage?action=voca_test5" method="post">
		<div class="question">
			<p><b>${matter10.getNum()}. ${matter10.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q1" value="2" required/>(1). ~���� �ռ� <br>
			<input type="radio" name="q1" value="1"/>(2). ����� ��, ������<br>
			</div>
		</div>	
		<div class="question">
			<p><b>${matter11.getNum()}. ${matter11.getMatter()}</b></p>
			<div>
			<input type="radio" name="q2" value="1" required />(1). ��) ������, (~����)�켱�ϴ�<br>
		 	<input type="radio" name="q2" value="2"/>(2). ��) �ʰ�, (~����)�ʴ� <br>

			</div>
		</div>
		<div class="question">
			<p><b>${matter12.getNum()}. ${matter12.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q3" value="1" required/>(1). ���� �� <br>
			<input type="radio" name="q3" value="2"/>(2). �����غ�<br>
			</div>
		</div>	
		<div style="padding-top:50px; text-align: left;">
			<input type="button" value="<< ���� " onclick="window.history.go(-1);"/>
			<input type="submit" value="����  >>" />
		</div>
		</form>
	</article>
	
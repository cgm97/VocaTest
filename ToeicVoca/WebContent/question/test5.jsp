<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
	<article>
		<form action="${pageContext.request.contextPath}/vocamanage?action=result" method="post">
		<div class="question">
			<p><b>${matter13.getNum()}. ${matter13.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q13" value="1" required/>(1). ���� <br>
			<input type="radio" name="q13" value="2"/>(2). ���л���<br>
			</div>
		</div>	
		<div class="question">
			<p><b>${matter14.getNum()}. ${matter14.getMatter()}</b></p>
			<div>
			<input type="radio" name="q14" value="1" required />(1). ��)�ٰ�����; ������<br>
		 	<input type="radio" name="q14" value="2"/>(2). ��)���� �� �̴� <br>

			</div>
		</div>
		<div class="question">
			<p><b>${matter15.getNum()}. ${matter15.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q15" value="2" required/>(1). ���������� <br>
			<input type="radio" name="q15" value="1"/>(2). ���� �����<br>
			</div>
		</div>	
		<div style="padding-top:50px; text-align: left;">
			<input type="button" value="<< ���� " onclick="window.history.go(-1);"/>
			<input type="submit" value="����  >>" />
		</div>
		</form>
	</article>
	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
	<article>
		<form action="${pageContext.request.contextPath}/vocamanage?action=voca_test3" method="post">
		<div class="question">
			<p><b>${matter4.getNum()}. ${matter4.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q4" value="2" required/>(1). �簳�� <br>
			<input type="radio" name="q4" value="1"/>(2). ���ú���<br>
			</div>
		</div>	
		<div class="question">
			<p><b>${matter5.getNum()}. ${matter5.getMatter()}</b></p>
			<div>
			<input type="radio" name="q5" value="1" required />(1). ��)������<br>
		 	<input type="radio" name="q5" value="2"/>(2). ��)���ͺ��ϴ� <br>

			</div>
		</div>
		<div class="question">
			<p><b>${matter6.getNum()}. ${matter6.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q6" value="2" required/>(1). ~���� ��ȣ�ϴ� <br>
			<input type="radio" name="q6" value="1"/>(2). ~�ϱ� ���� <br>
			</div>
		</div>	
		<div style="padding-top:50px; text-align: left;">
			<input type="button" value="<< ���� " onclick="window.history.go(-1);"/>
			<input type="submit" value="����  >>" />
		</div>
		</form>
	</article>
	
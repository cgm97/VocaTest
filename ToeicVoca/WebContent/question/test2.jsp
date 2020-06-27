<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
	<article>
		<form action="${pageContext.request.contextPath}/vocamanage?action=voca_test3" method="post">
		<div class="question">
			<p><b>${matter4.getNum()}. ${matter4.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q4" value="2" required/>(1). 재개발 <br>
			<input type="radio" name="q4" value="1"/>(2). 주택보수<br>
			</div>
		</div>	
		<div class="question">
			<p><b>${matter5.getNum()}. ${matter5.getMatter()}</b></p>
			<div>
			<input type="radio" name="q5" value="1" required />(1). 명)면접관<br>
		 	<input type="radio" name="q5" value="2"/>(2). 동)인터뷰하다 <br>

			</div>
		</div>
		<div class="question">
			<p><b>${matter6.getNum()}. ${matter6.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q6" value="2" required/>(1). ~보다 선호하다 <br>
			<input type="radio" name="q6" value="1"/>(2). ~하기 전에 <br>
			</div>
		</div>	
		<div style="padding-top:50px; text-align: left;">
			<input type="button" value="<< 이전 " onclick="window.history.go(-1);"/>
			<input type="submit" value="다음  >>" />
		</div>
		</form>
	</article>
	
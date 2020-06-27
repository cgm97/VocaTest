<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
	<article>
		<form action="${pageContext.request.contextPath}/vocamanage?action=voca_test5" method="post">
		<div class="question">
			<p><b>${matter10.getNum()}. ${matter10.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q1" value="2" required/>(1). ~보다 앞선 <br>
			<input type="radio" name="q1" value="1"/>(2). 제대로 된, 적법한<br>
			</div>
		</div>	
		<div class="question">
			<p><b>${matter11.getNum()}. ${matter11.getMatter()}</b></p>
			<div>
			<input type="radio" name="q2" value="1" required />(1). 형) 이전의, (~보다)우선하는<br>
		 	<input type="radio" name="q2" value="2"/>(2). 형) 늦게, (~보다)늦는 <br>

			</div>
		</div>
		<div class="question">
			<p><b>${matter12.getNum()}. ${matter12.getMatter()}</b></p>
			<div>
 			<input type="radio" name="q3" value="1" required/>(1). 성과 평가 <br>
			<input type="radio" name="q3" value="2"/>(2). 축제준비<br>
			</div>
		</div>	
		<div style="padding-top:50px; text-align: left;">
			<input type="button" value="<< 이전 " onclick="window.history.go(-1);"/>
			<input type="submit" value="다음  >>" />
		</div>
		</form>
	</article>
	
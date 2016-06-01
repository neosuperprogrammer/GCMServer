<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="viewport" content="width=device-width, user-scalable=no"/>
		<title>GCM Test 메인페이지</title>
		<style type="text/css">
			table { border-style: solid; border-width: 1px; border-spacing: 0px; border-collapse: collapse;}
			td { border-style: solid; border-width: 1px; padding: 3px; }
			th { font-family:바탕; text-decoration: underline; padding: 5px; }
		</style>
	</head>
<body>
	<br/><br/>
	<center><h2><a href="http://www.flowgrammer.com/" >flowgrammer's Blog</a></h2></center>
	<br/><br/>
	
	<!-- 푸시로 보낼 데이터 선택하는 폼 -->
	<form name="f" method="get" action="gcmSend.do">
		<table align="center">
			<thead><tr><th colspan="2">GCM 을 이용한 푸시 전송</th></tr></thead>
			<tr>
				<td align="right" style="width: 40px;">이름</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td align="right" >성별</td>
				<td><input type="radio" name="gender" value="남자"/>남 <input type="radio" name="gender" value="여자"/>여</td>
			</tr>
			<tr>
				<td align="right" >취미</td>
				<td>
					<input type="checkbox" name="hobby" value="농구"/>농구 
					<input type="checkbox" name="hobby" value="축구"/>축구
					<input type="checkbox" name="hobby" value="야구"/>야구
					<input type="checkbox" name="hobby" value="배구"/>배구
					<input type="checkbox" name="hobby" value="탁구"/>탁구
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="전송" style="width: 120px;"/></td>
			</tr>
		</table>
	</form>
</body>
</html>
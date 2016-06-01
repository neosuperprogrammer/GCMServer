<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no"/>
	<title>GCM 푸시 전송 후 결과 받기</title>
</head>
<body>
	<%
		out.print("GCM 전송 테스트<br/>");
		
		Object error = request.getAttribute("error");
		Object msgId = request.getAttribute("msgId");
		if(error != null)
			out.print("error : "+error.toString()+"<br/>");
		if(msgId != null)
			out.print("msgId : "+msgId.toString()+"<br/>");
	%>
</body>
</html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>구구단 보기</title>
</head>
<body>
<h3>구구단 보기</h3>
<%-- %
	int dan = 2;
	for(int i = 1; i <= 9; i++){
		out.print(dan + " * " + i + " = " + dan*i+"<br>");
		 out.print(String.format("%d * %d = %d<br>", dan, i, dan * i));
	}
%>
 --%>
	${gugu}
<p>
<a href = "/gugu?dan=2">2</a>
<a href = "/gugu?dan=3">3</a>
<a href = "/gugu?dan=4">4</a>
<a href = "/gugu?dan=5">5</a>
<a href = "/gugu/6">6</a>
<a href = "/gugu/7">7</a>
<a href = "/gugu/8">8</a>
<a href = "/gugu/9">9</a>
</body>
</html>
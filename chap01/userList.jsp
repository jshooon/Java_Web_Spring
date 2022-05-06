<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자 정보 리스트</title>
</head>
<body>
<h3>사용자 정보 리스트</h3>
<c:forEach var="u" items="${list}">
							<!-- detail까지는 컨트롤러의 실행url/ ?id는 파라미터값 -->
	<div>${u.uid} <a href="/mb/detail?id=${u.uid}">${u.name}</a> ${u.phone}</div>
</c:forEach>
[<a href = "/mb/add">사원정보 추가</a>]
</body>
</html>
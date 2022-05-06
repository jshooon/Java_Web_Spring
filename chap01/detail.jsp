<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이용자 상세정보</title>
<style>
	div {width : fit-content; padding :10px; border-bottom : 1px solid black;}
	div>label {display : inline-block; width : 60px; text-align: right; margin : 0 10px;}
	a {text-decoration: none;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">

	function deleted(id){
		
		if(!confirm('정말 삭제 하시겠습니까?')){
			return;
		}
		
		var obj = {};
		// id : key, detail.uid : value
		obj.id = '${detail.uid}';
		
		$.ajax({
			url : '/mb/delete',
			method : 'post',
			cache : false,
			data : obj,
			dataType : 'json',
			success : function(res){
				alert(res.deleted ? '삭제 성공' : '삭제 실패!');
				location.href = '/mb/list';
			},
			error : function(xhr, status, err){
				alert('에러 : ' + err);
			}
		});
		return;
	}
</script>
</head>
<body>
<h3>이용자 상세정보</h3>
	<div><label>아이디</label>${detail.uid}</div>
	<div><label>암호</label>${detail.pwd}</div>	 
	<div><label>이름</label>${detail.name}</div> 
	<div><label>번호</label>${detail.phone}</div>
	[<a href = "/mb/edit?id=${detail.uid}">수정</a>]
	[<a href = "javascript:deleted('${detail.uid}');">삭제</a>]
</body>
</html>
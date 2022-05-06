<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>유저정보 추가</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>

	var checkid = false;
	
	function add()
	{
		if(checkid == false){
			alert('아이디 중복을 확인 하세요.');
			return false;
		}
		var serData = $('#addForm').serialize();
		$.ajax({
			url:'/mb/add',
			method:'post',
			cache:false,
			data:serData,
			dataType:'json',
			success:function(res){
				alert(res.added ? '저장 성공' : '저장 실패');
				location.href="/mb/list";
			},
			error:function(xhr,status,err){
				alert('에러:'+err);
			}
		});
		return false;
	}
	
	function overLapId(){
		var uid = $('#uid').serialize();

		$.ajax({
			url : '/mb/idcheck',
			method : 'post',
			cache : false,
			data : uid,
			dataType : 'json',
			success : function(res){
// 				if(res.idcheck){
// 					alert('id사용가능');
// 					checkid = true;
// 				}else{
// 					alert('사용할 수 없는 id입니다.')
// 				}
				alert(res.idcheck ? 'id사용가능' : '사용할 수 없는 id입니다.');
				checkid = res.idcheck;
			},
			error : function(xhr, status, err){
				alert('에러 : ' + err);
			}
		});
		
	}
</script>
</head>
<body>
<h2>유저정보 추가 폼</h2>
<form id = "addForm" onsubmit = "return add();">
	<div><label for = "uid">아이디</label>
		<input type = "text" id = "uid" name = "uid">
		<button type = "button" onclick = "overLapId();">중복확인</button>
	</div>
	<div><label for = "pwd">비밀번호</label>
		<input type = "text" id = "pwd" name = "pwd">
	</div>
	<div><label for = "name">이름</label>
		<input type = "text" id = "name" name = "name">
	</div>
	<div><label for = "phone">전화번호</label>
		<input type = "text" id = "phone" name = "phone">
	</div>
	<div>
		<button type = "submit">저장</button>
		<button type = "reset">취소</button>
	</div>
</form>
</body>
</html>
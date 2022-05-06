<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>상세정보 수정</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	function update(){
		var serData = $('#updateForm').serialize();
		
		$.ajax({
			url : "/mb/update2",
			method : "post",
			cache : false,
			data : serData,
			dataType : 'json',
			success : function(res){
				alert(res.updated ? '수정 성공!' : '수정 실패!');
				location.href = "/mb/list"
			},
			error : function(xhr, status, err){
				alert('에러 : ' + err);
			}
		});
		return false;
	}
</script>
</head>
<body>
<h3>상세정보 수정</h3>
<form id = "updateForm" onsubmit = "return update();">
	<input type ="hidden" id = "uid" name = "uid" value ="${edit.uid}">
	<div><label>아이디</label>
		<input type = "text" name = "uid" value ="${edit.uid}" disabled></div>
	<div><label>이름</label><input type = "text" name = "name" value ="${edit.name}"readonly></div>
	<div><label>전화</label><input type = "text" name = "phone" value ="${edit.phone}"></div>
	<div>
		<button type = "submit">저장</button>
		<button type = "reset">취소</button>
	</div>
</form>
</body>
</html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>title</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	function selectChange(){
		var hobby = $('#hobby').val(); // select태그 id값
// 		alert('선택한 취미 : ' + hobby);
		
		var obj = {};
		obj.hobby = hobby;
		$.ajax({
			url : '/ui/form/select',
			method : 'post',
			cache : false,
			data : obj,
			dataType : 'json',
			success : function(res){
				//alert(JSON.stringify(res)); // 제이슨을 문자열로 변환.
				$('#res').text(res.msg);
			},
			error : function(xhr, status, err){
				alert('에러 : ' + xhr + status + err);
			}
			
		});
	}
	
	var delFile = new Array();
	
	function removeFile(elemId){
		alert(elemId);
		delFile.push(elemId);
		$('#' + elemId).remove();
		
	}
	function updateBoard(){
		// 수정된 글 데이터 
		// 수정된 첨부파일
		var obj = {};
		obj.delfile = delFile; // 배열을 json 오브젝트의 속성에 할당
		$.ajax({
			url :'/ui/form/arr',
			method:'post',
			cache:false,
			data:obj,
			dataType:'json',
			success:function(res){
				alert(JSON.stringify(res));
			},
			error:function(xhr, status, err){
				alert("에러"+err);
			}
		});
		return false;
	}
</script>
</head>
<body>
<h3>SELECT 이벤트 처리하기 예제</h3>
<form>
  <select id = "hobby" name = "hobby" onchange="selectChange();">
	<option value = "movie">영화</option>
	<option value = "game">게임</option>
	<option value = "trip">여행</option>
	<option value = "nap">낮잠</option>
  </select>
</form>
<hr>

<form onsubmit="return updateBoard();">
	<div id="file1"><label>file 1</label>
		<button type="button" onclick="removeFile('file1');">삭제</button></div>
	<div id="file2"><label>file 2</label>
		<button type="button" onclick="removeFile('file2');">삭제</button></div>
	<div id="file3"><label>file 3</label>
		<button type="button" onclick="removeFile('file3');">삭제</button></div>
	<p>
	<button type="submit" >저장</button>
</form>
<hr>
<p>
<div id = "res"></div>
</body>
</html>
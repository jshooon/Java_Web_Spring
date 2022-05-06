<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 수정</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	function update(){
		
			var formData = new FormData($("#updateForm")[0]); 
		    /*  var serData = $("#BoardForm").serialize();        */       
		   $.ajax({
		     url: "/bbs/update",         
		     method: "post",
		     enctype: 'multipart/form-data',
		     processData: false,
		     contentType: false,
		     cache: false,
		     data: formData,
		     dataType: "json",
		     success:function(res){            
		    	 alert(res.updated ? '수정 성공' : '수정 실패');
		         location.href="/bbs/list";
		      },
		     error:function(xhr,status,err){
		        alert("에러:" +xhr+status+err);
		     }
		     });     
		   return false;
		
	}
</script>
</head>
<body>
<h3>게시판 수정 폼</h3>
<form id = "updateForm" onsubmit = "return updated();" enctype = "multipart/form-data">
	<div><label for = "num">글번호</label>
		<input type = "text" id = "num" name = "num" value = "${edit.num}" readonly>
	</div>
	<div><label for = "author">작성자</label>
		<input type = "text" id = "author" name = "author" value = "${edit.author}" readonly>
	</div>
	<div><label for = "title">제목</label>
		<input type = "text" id = "title" name = "title" value = "${edit.title}" >
	</div>
	<div><label for = "contents">내용</label>
		<textarea name="contents" cols="40" rows="5" value = "${edit.contents}" ></textarea>
	</div>
	<div><label for = "files">첨부파일</label>
		<input type = "file" id ="files" name = "files" value =  multiple>
	</div>
	<div>
		<button type = "submit">저장</button>
		<button type = "reset">취소</button>
	</div>
</form>
</body>
</html>
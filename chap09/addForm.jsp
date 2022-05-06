<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글 쓰기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	function add(){
		var formData = new FormData($("#addForm")[0]); 
	    /*  var serData = $("#BoardForm").serialize();        */       
	   $.ajax({
	     url: "/bbs/add",         
	     method: "post",
	     enctype: 'multipart/form-data',
	     processData: false,
	     contentType: false,
	     cache: false,
	     data: formData,
	     dataType: "json",
	     success:function(res){            
	    	 alert(res.saved ? '저장 성공' : '저장 실패');
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
<h3>게시판 글 쓰기</h3>
<form id = "addForm" onsubmit = "return add();" method="post" enctype="multipart/form-data">
	<div><label for="author">작성자</label><input type="text" name="author" value="관리자"></div>
	<div><label for="title">제 목</label><input type="text" name="title" value="게시판 테스트"></div>
	<div><label for="contents">내 용</label><textarea name="contents" cols="40" rows="5"></textarea></div>
	<div><label>첨부파일</label><input type="file" id = "files" name="files" multiple></div>
	<div>
		<button type="submit">저장</button>
		<button type="reset">취소</button>
	</div>
</form>
</body>
</html>
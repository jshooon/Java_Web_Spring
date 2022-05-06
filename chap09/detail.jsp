<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- lengh함수를 사용하기위하여 사용 fn function약자 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 포멧을위하여 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 보기</title>
<style>
	h3 { width:fit-content; margin:1em auto;}
	table {width:fit-content; border:1px solid black; border-spacing: 0; margin:0 auto; }
	th,td { padding:5px; border-bottom:1px dashed black; }
	th { border-right: 1px solid black; }
	th:first-child { width: 60px; }
	td { width: 400px; }
	tr:last-child>th {border-bottom:none; }
	tr:last-child>td {border-bottom:none; }
	tr:nth-child(3) > td { height:70px; overflow: auto;}
	a { text-decoration: none; color:blue; }
	a.link_del { border:1px solid red; color:red; font-size: x-small;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	function delete_file(num)
	{
		if(!confirm('정말로 삭제하시겠어요?')){
			return;
		}
		var obj = {};
		obj.num = num;
		$.ajax({
			url:'/bbs/file/delete',
			method:'post',
			cache:false,
			data:obj,
			dataType:'json',
			success:function(res){
				alert(res.deleted ? '삭제 성공':'삭제 실패');
			},
			error:function(xhr,status,err){
				alert('에러:'+err);
			}
		});
	}
</script>
</head>
<body>
<h3>게시글 보기</h3>
<table>
<tr><th>글번호</th><td>${detail.num}</td></tr>
<tr><th>작성자</th><td>${detail.author}</td></tr>
<tr><th>제 목</th><td>${detail.title}</td></tr>
<tr><th>내 용</th><td>${detail.contents}</td></tr>
<tr><th>첨 부</th><td>
	<c:choose>		
		<c:when test="${fn:length(detail.attach)>0}">
			<c:forEach var="f" items="${detail.attach}"> 
				<fmt:formatNumber var="kilo" value="${f.filesize/1024}" maxFractionDigits="0" />
				<div>${f.num}. 
					<a href="/bbs/file/download/${f.num}">${f.filename}</a>(${kilo} kb) 
					<a class="link_del" href="javascript:delete_file(${f.num});">삭제</a>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			첨부파일 없음
		</c:otherwise>
	</c:choose>
</td></tr>
</table>
<div>
	<a href = "/bbs/edit?num=${detail.num}">수정</a>
</div>
<img src = "/images/sample.jpg">
</body>
</html>








<!--
<%-- <%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 보기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function deleted(num){
		var obj = {};
		obj.num = num;
		
		$.ajax({
			url : '/bbs/delete',
			method : 'post',
			cache : false,
			data : obj, 
			dataType : 
		})
	}
</script>
</head>
<body>
<h3>게시글 보기</h3>
<div>${detail.num} ${detail.title} </div>
<div>${detail.contents}</div>
<div>
	<div>첨부파일</div>
	<c:forEach var="f" items="${detail.attach}">
		<div>${f.num}.
			<a href="/bbs/file/download/${f.num}">${f.filename}</a>(${f.filesize}) </div>
	</c:forEach>
</div>
<div>
	<button>수정</button>
	[<a href = "/bbs/delete/${detail.num}">삭제</a>]
	<button type = "button", onclick = "deleted(${detail.num})">삭제</button>
</div>
</body>
</html>
-->
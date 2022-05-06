<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CSS Position Property Test2</title>
<style type="text/css">
 	div {display: inline-block;}  
	div { width : 100px; height : 100px; border :1px solid black;}
	#div1 {background-color : red; position:absolute; top : 100px; left : 300px; } /* position:absolute : 태그들의 기본 흐름을 완전 무시한다. 태그가 있던 없던 지정값으로 움직인다. */
	#div2 {background-color : green; position:absolute; top : 100px; left : 200px; } /* 절대위치 바디태그안에 지정값 자리로 간다. 다른태그들도 인식을 하지 않는다. */
	
</style>
</head>
<body>
<h2>CSS Position Test2</h2>
<!--  main(주요부분), header(위쪽), footer(아래쪽), article, section(주제), aside, nav(경로)...
	  전부 div태그와 같고 이름만 다르다. 따라서 정의에 맞게 사용하면 된다. 
	  다 블럭이기때문에 한행씩 영역을 가지기때문에  옆에 다른 태그들이 올수 없다. 
	  따라서 속성을 사용하여 옆으로 올 수 있게 수정한다..-->
	  
	<div id = "div1"></div>
	<div id = "div2"></div>






</body>
</html>
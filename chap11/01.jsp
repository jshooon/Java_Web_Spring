<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CSS Position Property Test</title>
<style type="text/css">
 	div {display: inline-block;}  
	div { width : 100px; height : 100px; border :1px solid black;}
	#div1 {background-color : red; position:relative; } /* position:relative : 기준점 상대를 정해준다.*/
	#div2 {background-color : green; position : relative; top : 50px; left : 50px; } /*div2의 부모중에서 포지션속성을 갖게되는 태그를 기점으로 50 , 50이 떨어진다.*/
	#div3 {background-color : blue; position : relative; top : 50px; left : 50px;}
	
</style>
</head>
<body>
<h2>CSS Position Test</h2>
<!--  main(주요부분), header(위쪽), footer(아래쪽), article, section(주제), aside, nav(경로)...
	  전부 div태그와 같고 이름만 다르다. 따라서 정의에 맞게 사용하면 된다. 
	  다 블럭이기때문에 한행씩 영역을 가지기때문에  옆에 다른 태그들이 올수 없다. 
	  따라서 속성을 사용하여 옆으로 올 수 있게 수정한다..-->
	  
<div id = "div1">DIV1
	<div id = "div2">DIV2
		<div id = "div3">DIV3</div>
	</div>
</div>



</body>
</html>
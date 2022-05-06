<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CSS Float Property</title>
<style type="text/css">
	#container01 > div. {border : 1px solid black; width : 70px; 
		text-align: center; margin-right : 5px;}
		.r {float : right;}
		.l {float : left;}
		#dumb {clear : both;}
		#container02 {margin-top : 1em; width : 300px;} /* float가 있으면 무시하지만 clear를함으로 써 float을 한 행을 인정하며 다음행으로 간다. */
		#container02 > img {float : left; margin-right: 10px;}
</style>
</head>
<body>
<div id = "container01">
	<div class = "r">A</div>
	<div class = "r">B</div>
	<div class = "r">C</div>
	<div class = "l">D</div>
	<div class = "l">E</div>
</div>
<div id = "dumb"></div>
<div id = "container02">
	<img src = "/images/sample.jpg">
	
</div>

</body>
</html>
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
		#container02 {margin-top : 1em; width : 300px; boder : 1px solid black; padding : 5px;} /* float가 있으면 무시하지만 clear를함으로 써 float을 한 행을 인정하며 다음행으로 간다. */
		#container02 :: after {contents :""; clear : both; display : table;} /*container02태그 뒤에 빈 태그를 주며 플롯트 효과를 미치지 않게 하는 동시에 한행의 테이블로 만드는 법.*/ 
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
	<img src = "/images/스크린샷(10).png">
	문자(文字, Writing System)는 언어를 기록하기 위한 상징 체계이다. 거의 대부분 음성 언어를 기록하기 위해 생겨나거나 생성되었다. 또한 메시지를 전달하기 위해 문자와 구어(口語)는 둘 다 유용하지만, 문자의 경우 정보의 저장과 전달을 신뢰할 수 있다는 점에서 차이가 있다. 인류가 최초로 사용한 문자는 기원전 3천 년 경의 쐐기 문자이다.
</div>

</body>
</html>
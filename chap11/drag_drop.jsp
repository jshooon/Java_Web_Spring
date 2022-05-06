<%@ page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Drag and Drop examples</title>
<style type="text/css">
	.container { display:flex; padding:1em; width:500px;}
	.container > div { border:1px solid black; display:inline-block; 
			width:220px; margin:1em; }
	#target { width:1000px;  }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
// 드래그가 일어나는곳.
function dragstart_handler(ev) // 이벤트 객체가 ev로 들어간다.
{
	ev.dataTransfer.dropEffect = "copy"; // drop할때 복사 할 것이다라는것.
	console.log("dragStart"); // 개발자도구 콘솔을 출력한다는것. 개발자가 확인하기 위함.
	// 데이터 전달 객체에 대상 요소의 id를 추가합니다.
	ev.dataTransfer.setData("text/plain", ev.target.id); // 데이터를 넣는 방법. 이벤트를 일으킨 태그(마우스로 끌리기시작한 태그)의 아이디속성을 가져온다는것.
	// dataTransfer : 데이터를 전달. 끌어서 놓는곳에 데이터(텍스트 id를 전달한다.)를 저장한다. 
	if(ev.target instanceof Image){ // 끌려오는 태그가 이미지를 안다면, 이미지라면 setDragImage를 사용한다.
		ev.dataTransfer.setDragImage(ev.target, 10, 10); //드래그 시 마우스포인터 위치 : 10px ㅣ 10px
	}
}

function dragover_handler(ev) // 태그를 끌어서 드랍하는 태그에 놓았을때 발생태그 
{
	 ev.preventDefault();
	 // dropEffect를 move, copy 등으로 설정.
	 ev.dataTransfer.dropEffect = "copy";
}

function drop_handler(ev) // 마우스를 땔때
{
	ev.preventDefault();
	// 대상의 id를 가져와 대상 DOM에 움직인 요소를 추가합니다.
	const data = ev.dataTransfer.getData("text/plain"); // 태그의 아이디를 data에 넣는다.
	var elem = document.getElementById(data) // 태그의아이디를 넣어서 태그객체를 구한다. DOM(Document Object Model)웹문서를 객체로 표현하기위한 모형 id를 이용하여 DOM 오브젝트를 만든다.
	ev.target.appendChild(elem); // A에서B로 넣는다. elem은 DOM오브젝트. appendChild(elem) : 마우스를 땐곳의 태그아래에다 elem돔객체를 붙여넣겠다는 것. 
}

function dragend_handler(evt)
{
	//alert('드래그 종료');
}

window.addEventListener('DOMContentLoaded', () => {
	// id를 통해 element를 가져옵니다.
	const element1 = document.getElementById("item1");
	// 'dragstart' 이벤트 리스터를 추가합니다.
	element1.addEventListener("dragstart", dragstart_handler);
	// dragstart는 변경할 수 없다. item1 마우스로 끌기 시작하면 dragstart_handler가 작동한다.
	const element2 = document.getElementById("item2");
	element2.addEventListener("dragstart", dragstart_handler);
	
});

function showAll()
{
	var arr = new Array();
	
	$('#target').children().each(function(index, item){ // 데이터가 여러개기때문에 children메소드를 사용하고, children에 들어있는 객체들을 하위태그를 각각 each안의 익명 함수에 넣겠다라는 것. 여러개 넣었을때
		arr.push($(item).attr('data-item')); // 표준 오브젝트를 제이쿼리로 랩핑한뒤 attr을 사용해야한다. attr = 속성이다. data-item 속성의값.
	});
	
	alert(arr);
	
	var obj = {}; 
	obj.fruits = arr; // 배열에 하나하나 넣겠다라는 뜻.
}
</script>
</head>
<body>
<h3>Drag and Drop examples</h3>
<div class="container">
	<div id="item1"  draggable="true" data-item="수박"  data-price="10000">
		품목 : 수박
		가격 : 10000
	</div>
	<img src="/images/flower.jpg" id="item2"  draggable="true" data-item="flower"  data-price="5000">
	<div id="target" ondrop="drop_handler(event)" 
			ondragover="dragover_handler(event)"
			ondragend="dragend_handler(event)">
	</div>
</div>
<button type="button" onclick="showAll()">확인</button>
</body>
</html>
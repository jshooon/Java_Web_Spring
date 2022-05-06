<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>웹소켓 테스트 페이지</title>
<script type="text/javascript">
var g_webSocket = null;
window.onload = function() { // 자바스크립트 최상위 오브젝트 window(화면)onload(이벤트) 웹페이지가 로드되면 실행되는 함수
    //host = "152.70.92.222";   /* 배포시에 호스트 주소로 변경 */
   host = "localhost"; // 개발서버기때문에 localhost다.
    g_webSocket = new WebSocket("ws://"+host+":8888/websocket");

    
    
    /* 웹소켓 접속 성공시 실행 */
    g_webSocket.onopen = function(message) {
        addLineToChatBox("Server is connected.");
    };
    
    
    /* 웹소켓 서버로부터 메시지 수신시 실행 */
    g_webSocket.onmessage = function(message) { //message 객체다.
        addLineToChatBox(message.data);
    };

    /* 웹소켓 이용자가 연결을 해제하는 경우 실행 */
    g_webSocket.onclose = function(message) {
        addLineToChatBox("Server is disconnected.");
    };

    /* 웹소켓 에러 발생시 실행 */
    g_webSocket.onerror = function(message) {
        addLineToChatBox("Error!");
    };
} // window.onload()끝

/* 채팅 메시지를 화면에 표시 */
function addLineToChatBox(_line) {
    if (_line == null) {
        _line = "";
    }
    
    var chatBoxArea = document.getElementById("chatBoxArea");
    chatBoxArea.value += _line + "\n"; // 메세지 추가
    chatBoxArea.scrollTop = chatBoxArea.scrollHeight; // 메세지가 꽉차면 , 메세지가 항상 보이도록 설정하는 것.  
}

/* Send 버튼 클릭하면 서버로 메시지 전송 */
function sendButton_onclick() {  // document : 웹문서
    var inputMsgBox = document.getElementById("inputMsgBox"); // 엔터키를 치면 인풋박스 
    // 제이쿼리 사용시, $('#inputMsgBox').val(); 위와 같다.
    if (inputMsgBox == null || inputMsgBox.value == null || inputMsgBox.value.length == 0) { // 입력값이 없을때
        return false;
    }
    
    var chatBoxArea = document.getElementById("chatBoxArea");
    
    if (g_webSocket == null || g_webSocket.readyState == 3) {
        chatBoxArea.value += "Server is disconnected.\n";
        return false;
    }
    
    // 서버로 메시지 전송
    g_webSocket.send(inputMsgBox.value); // g_webSocket 웹소켓 객체이고 서버로 데이터 전달.
    inputMsgBox.value = "";
    inputMsgBox.focus();
    
    return true;
}

/* Disconnect 버튼 클릭하는 경우 호출 */
function disconnectButton_onclick() {
    if (g_webSocket != null) {
        g_webSocket.close();    
    }
}

/* inputMsgBox 키 입력하는 경우 호출 */
function inputMsgBox_onkeypress() {
    if (event == null) {
        return false;
    }
    
    // 엔터키 누를 경우 서버로 메시지 전송
    var keyCode = event.keyCode || event.which; // 여러개의 웹브라우저들마다 다르기 때문에 어떤 브라우저든지 이벤트 키코드를 받겠다.
    if (keyCode == 13) { //엔터키는 코드가 13이다. 코드는 숫자.
        sendButton_onclick(); //메소드가 호출된다.
    }
}
</script>
</head>
<body>
    <input id="inputMsgBox" style="width: 250px;" type="text" onkeypress="inputMsgBox_onkeypress()">
    <input id="sendButton" value="Send" type="button" onclick="sendButton_onclick()">
    <input id="disconnectButton" value="Disconnect" type="button" onclick="disconnectButton_onclick()">
    <br/>
    <textarea id="chatBoxArea" style="width: 100%;" rows="10" cols="50" readonly="readonly"></textarea>
</body>
</html>
package com.tjoeun.ws;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
//@ServerEndpoint("/websocket") // 웹소켓의 서버.
@ServerEndpoint(value = "/websocket", configurator=HttpSessionConfig.class)
public class WebSocketTest 
{
    /* 웹소켓 세션 보관용 ArrayList */
    private static ArrayList<Session> sessionList = new ArrayList<Session>();

    /* 웹소켓 사용자 접속시 호출됨  */
    @OnOpen // 이용자가 접속이 열렸을 때 돌아가는 메소드
    public void handleOpen(Session session, EndpointConfig config) {// 이용자가 메세지를 보내면 세션도 따라다닌다.
    	HttpSession httpSession = (HttpSession) config.getUserProperties().get("session");
    	String uid = (String) httpSession.getAttribute("uid");
    	System.out.println("웹소켓에 전달된 이용자 ID : " + uid);
        if (session != null) {
            String sessionId = session.getId(); // 웹에서 주어진 이용자 아이드를 변수에 저장. 이용자를 추적
            
            System.out.println("client is connected. sessionId == [" + sessionId + "]");
            sessionList.add(session); // 아이디를 저장한다면 몇명 접속햇는지, 이용자가 어떤 이용자랑 연락하고싶은지 알 수 있다.
            
            /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
            sendMessageToAll("--> [USER-" + sessionId + "] is connected. ");
            // 현재 접속한 이용자에게 접속 메세지 전송.
        }
    }

    /* 웹소켓 이용자로부터 메시지가 전달된 경우 실행됨 */
    @OnMessage // 서버에서 채팅 메세지를 받았을때. 메세지와, 세션도 들어온다. 이용자에게 받은 메세지를 이용자에게 보내지 않을 수 있다.
    public String handleMessage(String message, Session session) {
        if (session != null) {
            String sessionId = session.getId();
            System.out.println("message is arrived. sessionId == [" + sessionId + "] / message == [" + message + "]");

            /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
            sendMessageToAll("[USER-" + sessionId + "] " + message);
        }

        return null;
    }

    /* 웹소켓 이용자가 연결을 해제하는 경우 실행됨 */
    @OnClose
    public void handleClose(Session session) { // 접속을 끊은 이용자의 세션이 파라미터로 들어온다.
        if (session != null) {
            String sessionId = session.getId();
            System.out.println("client is disconnected. sessionId == [" + sessionId + "]");
            
            /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
            sendMessageToAll("***** [USER-" + sessionId + "] is disconnected. *****");
        }
    }

    /* 웹소켓 에러 발생시 실행됨 */
    @OnError
    public void handleError(Throwable t) { // Throwable객체에 에러가 파라미터에 들어온다.
        t.printStackTrace();
    }
    
    
    /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
    private boolean sendMessageToAll(String message) {
        if (sessionList == null) {
            return false;
        }

        int sessionCount = sessionList.size();
        if (sessionCount < 1) {
            return false;
        }
        
        // 세션 객체 생성
        Session singleSession = null;

        for (int i = 0; i < sessionCount; i++) {
            singleSession = sessionList.get(i); // 세션에서 이용자 아이디를 변수에 저장
            if (singleSession == null) {
                continue;
            }

            if (!singleSession.isOpen()) {
                continue;
            }
            //세션 하나 구하여 메세지를 보낸다. 이것을 리스트에 들어있는 유저 아이디에 메세지를 보낸다. 반복
            sessionList.get(i).getAsyncRemote().sendText(message);
        }	// 세션에대한 비동기 원격 객체를 구하여, 클라이언트 정보를 구하여 메세지를 보낸다.

        return true;
    }
}
package net.koreate.test.controller;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import net.koreate.test.vo.ValidationMemberVO;

/*
 *  중복 로그인 방지
 *  implements HttpSessionListener, HttpSessionAttributeListener
*/

@Component
public class SessionEventListener implements HttpSessionListener, HttpSessionAttributeListener{
	
	public static SessionEventListener sessionManager = null;
	
	public static Hashtable<String,Object> sessionRepogitory;
	
	public SessionEventListener() {
		System.out.println("SessionEventListener - CREATED");
		if(sessionRepogitory == null) {
			sessionRepogitory = new Hashtable<String,Object>();
		}
	}
	
	public static SessionEventListener getInstace() {
		if(sessionManager == null) {
			sessionManager = new SessionEventListener();
		}
		return sessionManager;
	}
	
	public boolean duplicatedSession(String u_id,String sessionId) {
		boolean result = false;
		
		Enumeration<Object> enumeration = sessionRepogitory.elements();
		System.out.println("active session count : " + sessionRepogitory.size());
		System.out.println("u_id : " + u_id + "sessionid : " + sessionId);
		while(enumeration.hasMoreElements()) {
			HttpSession registerSession = (HttpSession)enumeration.nextElement();
			ValidationMemberVO vo = (ValidationMemberVO)registerSession.getAttribute("userInfo");
			
			if(vo != null) {
				if(vo.getU_id().equals(u_id)) {
					if(!registerSession.getId().equals(sessionId)) {
						System.out.println("duplicatedSession login - user : " + vo.getU_id() + " session id : " + registerSession.getId());
						registerSession.invalidate();
						result = true;
					}
				}
			}
		}
		
		return result;
	}
	
	

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("attributeAdded 호출");
		if(event.getName().equals("userInfo")) {
			
			synchronized (sessionRepogitory) {
				HttpSession session = event.getSession();
				System.out.println("SESSION REGISTER" + session.getId());
				sessionRepogitory.put(session.getId(), session);
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		synchronized (sessionRepogitory) {
			System.out.println("SESSION DESTROY : " + session.getId());
			sessionRepogitory.remove(session.getId());
		}
	}

}

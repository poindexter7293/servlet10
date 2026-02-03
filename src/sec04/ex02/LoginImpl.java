package sec04.ex02;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//웹 애플리케이션에서 사용자의 로그인 상태를 관리하는 클래스 입니다.
//HttpSessionListener인터페이스를 구현하여 HttpSession객체가 생성되거나 소멸될때 동작합니다.

@WebListener
public class LoginImpl implements HttpSessionListener{

	String user_id; //사용자의 아이디를 저장하는 변수
	String user_pw; //사용자의 비밀번호를 저장하는 변수 
	static int total_user = 0; //현재 로그인한 사용자 수를 저장하는 정적(static)변수
	
	
	//기본생성자 (매개변수가 없는 생성자)
	public LoginImpl() {
		//객체가 생성될때 별다른 초기화 작업을 하지 않습니다.
	}
	
	//로그인 요청한 사용자의 아이디와 비밀번호를 매개변수로 받아 초기화하는 생성자입니다.ㄴ
	public LoginImpl(String user_id, String user_pw) {
		this.user_id = user_id;
		this.user_pw = user_pw;
	}

	// 사용자가 로그인 하여 HttpSession객체 메모리가 생성될때 실행되는 메소드입니다.
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("HttpSession객체 메모리 생성");//새로운 세션이 생성되었음을 테스트.
		++total_user; //로그인 한 사용자 수를 1증가 시킵니다.
	}

	// 사용자가 로그아웃 하거나  HttpSession객체가 톰캣 메모리에서 만료되어 소멸될때 실행되는 메소드입니다.
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("HttpSession객체 메모리 소멸");
		total_user--;//로그인 한 사용자 수를 1감소 시킵니다.
	}
	
}







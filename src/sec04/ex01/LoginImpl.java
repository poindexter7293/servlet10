package sec04.ex01;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


//이벤트 처리하는 이벤트 핸들러 역할의 클래스로
//추상메소드를 강제로 오버라이딩 해서  HttpSession객체 메모리에 바인딩 시~~ 이벤트를 처리하는 클래스입니다

//HttpSessionBindingListener를 제외한 Listener를 구현하는 모든 이벤트 핸들러는 반드시 
//아래의 애노테이션 기호를 이용해서 Listener로 등록해야 합니다. 
@WebListener // 이 어노테이션은 이 클래스가 ServletContext의 리스너로 동작한다는 것을 톰캣에 알려줌
             //즉, 서블릿 컨텍스트가 시작되거나 종료될 때 특정 작업을 수행할 수 있게 톰캣에 알려줌
public class LoginImpl implements HttpSessionBindingListener { 

    // 사용자가 로그인할 때 입력한 아이디와 비밀번호를 저장할 변수입니다.
    String user_id; 
    String user_pw;

    // static 변수로, 전체 접속자의 수를 저장합니다. static 변수는 모든 객체에서 공유되므로 로그인한 모든 사용자의 접속자 수를 추적할 수 있습니다.
    static int total_user = 0;

    // 기본 생성자: 객체를 생성할 때 사용되는 기본적인 생성자입니다.
    public LoginImpl() {}

    // 사용자가 로그인할 때 입력한 아이디와 비밀번호를 받아 객체를 초기화하는 생성자입니다.
    public LoginImpl(String user_id, String user_pw) {
        this.user_id = user_id; // 사용자 아이디 초기화
        this.user_pw = user_pw; // 사용자 비밀번호 초기화
    }

    // HttpSession 객체가 메모리에 바인딩될 때 호출되는 메소드입니다. 즉, 사용자가 로그인하면 이 메소드가 실행됩니다.
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("사용자 접속"); // 로그를 찍어 사용자가 접속했음을 확인할 수 있습니다.
        ++LoginImpl.total_user; // 전체 접속자 수를 1 증가시킵니다.
    }

    // HttpSession 객체가 메모리에서 해제될 때 호출되는 메소드입니다. 즉, 사용자가 로그아웃하면 이 메소드가 실행됩니다.
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("사용자 접속 해제"); // 로그를 찍어 사용자가 접속을 해제했음을 확인할 수 있습니다.
        total_user--; // 전체 접속자 수를 1 감소시킵니다.
    }
}








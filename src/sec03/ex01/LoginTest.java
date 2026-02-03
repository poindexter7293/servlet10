package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//login.html 에서 로그인 요청시 <form> 태그의 action속성에 작성된 login 서블릿 매핑 주소로 요청하면 요청 받는 서블릿 
@WebServlet("/login")
public class LoginTest extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		모든 인코딩 작업을  각각의 서블릿페이지에서 하지 말고 Filter관련 클래스 내부에서 공통작업으로 처리하자.
		
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		response.setCharacterEncoding("utf-8");
		*/
		
		PrintWriter out = response.getWriter();
		
		//실제 작업
		//1. 로그인 요청시 입력받았던 이름, 비밀번호 등 요청한 데이터 얻기
		String user_name = request.getParameter("user_name");
		String user_pw = request.getParameter("user_pw");
		
		//2. 입력받은 이름과 비밀번호를 클라이언트의 웹브라우저로 출력(응답)해 확인 시켜 주자.
		out.print("<html><body>이름은 : " + user_name + "<br> 비밀번호는 : " + user_pw + "<br></body></html>");
		
		
	}

}









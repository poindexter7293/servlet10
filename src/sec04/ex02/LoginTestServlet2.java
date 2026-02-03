package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//로그인 요청 받는 서블릿 
@WebServlet("/loginTest2")
public class LoginTestServlet2 extends HttpServlet {
	
	//웹 애플리케이션의 컨텍스트 정보를 저장하는 공유객체(서블릿 페이지간의 값을 공유 할수 있음)
	ServletContext context = null;
	
	//로그인한 사용자들의 ID를 저장하는 리스트(모든 사용자의 로인 정보를 저장)
	List<String> user_list = new ArrayList<>();
	
	//login2.html에서 로그인 요청하면 실행되는 메소드(POST 방식 요청 처리)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트에서 전송된 데이터를 처리할때 한글이 꺠지지 않도록 인코딩 방식 UTF-8설정
		request.setCharacterEncoding("UTF-8");
		
		//톰캣서버가 실행하는 서블릿에서 클라이언트의 웹브라우저로 보내는 응답데이터 MIME-TYPE설정 및 인코딩 방식 UTF-8설정
		response.setContentType("text/html;charset=utf-8");
		
		//현재 실행 중인 웹 애플리케이션의 ServletContext객체를 가져옴 (톰캣이 미리 생성 해놓음)
		context = getServletContext();
		
		//클라이언트에게 응답을 출력할수 있는 PrintWriter출력스트림 객체 생성
		PrintWriter out = response.getWriter();
		
		//사용자가 입력한 ID와 비밀번호를 받아옴
		String user_id = request.getParameter("user_id"); //입력된 사용자 ID
		String user_pw = request.getParameter("user_pw"); //입력된 사용자 비밀번호
		
		//사용자의 로그인 정보를 저장하는 LoginImpl객체 생성 
		LoginImpl loginUser = new LoginImpl(user_id, user_pw);
		
		//사용자의 HttpSession객체 를 생성 (만약 존재하지 않으면 새로운 HttpSession객체 생성)
		HttpSession session = request.getSession();
		
		//사용자 로그인 접속시 요청한 새로 만든 HttpSession이면?
		if(session.isNew()) {
			//HttpSession영역에 이벤트 핸들어 역할을 하는 LoginImpl클래스의 객체주소를 바인딩
			session.setAttribute("loginUser", loginUser);
			
			//로그인한 사용자의 ID(문자열객체)를 리스트에 추가(로그인 한 사용자 관리하기 위해)
			user_list.add(user_id);
			
			//웹 애플리케이션의 컨텍스트객체에 사용자의 ID가 저장된 ArrayList(리스트)를 바인딩 (모든 서블릿에서 공유 가능)
			context.setAttribute("user_list", user_list);// ArrayList(리스트)를 바인딩		
		}//if
		
		
		//클라이언트에게 로그인 정보를 HTML태그 형식으로 응답(출력)
		out.println("<html><body>");
		
		//로그인한 사용자의 ID 출력
		out.println("아이디는 " + loginUser.user_id + "<br>"  );
		
		//현재 로그인한 사용자 수 출력(LoginImpl클래스의 static 정적변수 사용)
		out.println("총 접속자수는 " + LoginImpl.total_user + "명<br><br>");
		
		//현재로그인한 사용자 목록 출력
		out.println("접속 아이디 목록:<br>");
		
		//ServletContext객체 메모리 영역에 저장된(바인딩된) ArrayList배열(로그인한 사용자들의 ID 저장되어 있음) 얻기
		List<String> list = (ArrayList<String>)context.getAttribute("user_list");
		
		for(String  id  : list  ) {
			out.println(id + "<br>"); //로그인한 사용자 ID 들 반복해서 출력
		}
		
		// 로그아웃 링크 제공 (클릭하면 로그아웃을 처리하는 서블릿으로 이동)
        out.println("<a href='logout?user_id=" + user_id + "'>로그아웃</a>");
		
		out.println("</body></html>");
		
	}//doPost

}














package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//사용자가 로그아웃 요청을 하면 HttpSession객체를 제거하고, ArrayList에서 로그아웃요청한 사람의 ID를 삭제하는 서블릿
// - 로그아웃처리 서블릿
@WebServlet("/logout")
public class LogoutTest2 extends HttpServlet {
	
	//웹 애플리케이션 전체 공유 메모리 영역인 ServletContext객체 메모리 영역 저장할 변수 
	ServletContext context;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//요청한 데이터 인코딩 방식 UTF-8처리
		request.setCharacterEncoding("UTF-8");
		
		//웹브라우저로 응답할 데이터 인코딩 방식 UTF-8처리 및 MIMETYPE 설정
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//출력 스트림
		PrintWriter out = response.getWriter();
		
		//웹 애플리케이션 전체 공유 메모리 영역인 ServletContext객체 얻어 초기화
		context = this.getServletContext();
		
		//현재 사용자의 HttpSession객체 얻기 (없으면 null반환 할것임)
		HttpSession session = request.getSession();
		
		//클라이언트가 로그아웃 요청시 보낸 user_id 값을 HttpServletRequest메모리에서 꺼내오기
		//->out.println("<a href='logout?user_id=" + user_id + "'>로그아웃</a>"); 클릭하여 요청한 아이디
		String user_id = request.getParameter("user_id");
		
		//현재 로그아웃 요청한 사용자의 HttpSession을 삭제하여 로그아웃처리
		session.invalidate();
		
		//로그인한 사용자들의 ID 목록을 저장한 ArrayList배열 ServletContext객체 메모리 영역에서 얻기
		List<String> user_list = (ArrayList<String>)context.getAttribute("user_list");
		
		//현재 로그아웃을 요청한 사용자의 ID를 ArrayList배열에서 삭제
		user_list.remove(user_id);
		
		//기존의 사용자ID가 저장된 ArrayList배열을 ServletContext영역에서 제거하고,
		//변경된 ~~~ ArrayList배열을 다시 ServletContext영역에 저장해서 업데이트 함
		context.removeAttribute("user_list"); //제거 하고 
		context.setAttribute("user_list", user_list); // 저장
		
		//로그아웃 완료 메세지를 클라이언트의 웹브라우저로 응답
		out.println("<br>로그아웃 했습니다.");
		
	}//doGet

}










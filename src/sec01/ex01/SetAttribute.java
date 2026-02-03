package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/set")
public class SetAttribute extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		
		String ctxMesg = "ServletContext 객체 메모리에 바인딩 할 특정 문자열 값";
		String sesMesg = "HttpSession객체 메모리에 바인딩 할 특정 문자열 값";
		String reqMesg = "HttpServletRequest 객체 메모리에 바인딩 할 특정 문자열 값";
		
		ServletContext servletContext = this.getServletContext();
		HttpSession httpSession = req.getSession();
		
		servletContext.setAttribute("context", ctxMesg);
		httpSession.setAttribute("session", sesMesg);
		req.setAttribute("request", reqMesg);
		
		out.print("각각 서블릿 관련 객체 메모리 영역들에 바인딩했습니다.");
		
	}
	
}

	


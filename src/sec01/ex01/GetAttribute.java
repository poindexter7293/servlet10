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

@WebServlet("/get")
public class GetAttribute extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		ServletContext servletContext = this.getServletContext();
	
		String ctxMesg = (String)servletContext.getAttribute("context");
		out.print("ServletContext 서블릿 관련 객체 메모리 영역에서 꺼내온 값 : "+ctxMesg+"<br>");
		
		HttpSession httpSession = req.getSession();
		
		String sesMesg = (String)httpSession.getAttribute("session");
		out.print("HttpSession 서블릿 관련 객체 메모리 영역에서 꺼내온 값 : "+sesMesg+"<br>");
		
		String reqMesg = (String)req.getAttribute("request");
		out.print("HttpServletRequest 서블릿 관련 객체 메모리 영역에서 꺼내온 값 : "+reqMesg+"<br>");
		
		
		servletContext.setAttribute("context", ctxMesg);
		httpSession.setAttribute("session", sesMesg);
		req.setAttribute("request", reqMesg);
		
		out.print("각각 서블릿 관련 객체 메모리 영역들에 바인딩했습니다.");
		
	}

}

	


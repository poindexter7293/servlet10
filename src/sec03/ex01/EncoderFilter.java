package sec03.ex01;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/*
	사용자 정의 EncoderFilter 클래스를 만들때...
	반드시 제공되는 Filter 인터페이스 내부에 작성된 추상메소드들(init, doFilter, destory)을 강제로 메소드 오버라이딩 해서 만듭니다.
	

*/

@WebFilter("/*")
public class EncoderFilter  extends HttpFilter   implements Filter {

	ServletContext servletContext;
	
	/*
	 init 메소드 
	 - 클라이언트가 웹브라우저를 이용해 LoginTest서블릿 요청시 
	   톰캣 서버가  웹프로젝트 하나당 만들어 주는 ServletContext 서블릿 관련 객체 메모리를 하나 얻어  변수에 저장(변수 초기화) 하는 역할.
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
		System.out.println("UTF-8 방식으로 인코딩 ........");
		
		this.servletContext = fConfig.getServletContext();
	
	}
	/*
	 doFilter 메소드
	  -  요청 받은 모든 서블릿 페이지에서 공통 적으로 요청을 받았을때의 요청한 데이터 중 한글문자가 깨지지 않도록 인코딩 방식 UTF-8로 설정 하여 한글처리 
	  -  웹브라우저를 이용해 LoginTest서블릿 요청시 doFilter메소드의 매개변수로 HttpServletRequest와 HttpServletResponse객체가 전달되며,
	     doFilter메소드는 FilterChain chain세번째 매개변수를 가집니다.
	         첫번째 매개변수 request로 전달 받은 HttpServletRequest객체의 정보를 이용해 문자처리방식을 UTF-8로 설정해 작업합니다.
	        세번째 매개변수로  chain로 전달 받은 FilterChanin객체의 doFilter메소드를 호출하는 위치를 기준으로 위쪽에 작성한 코드는 요청 필터 기능을 수행하고,
	     						    FilterChain객체의 doFilter메소드를 호출하는 위치를 기준으로 아래쪽에 작성한 코드는 응답 필터 기능을 수행 하게 됩니다.
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("doFilter 메소드가 호출되어 실행중");
		
		/* 모든 인코딩 작업을  각각의 서블릿페이지에서 하지 말고 Filter관련 클래스 내부의 doFilter메소드 에서 공통작업으로 처리하자. */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");		
		response.setCharacterEncoding("utf-8");
		
		/*다른 공통 작업 가능*/
		//1.클라이언트가 요청한 전체 URL주소 중에서 컨텍스트 주소 얻어 저장
		String contextPath = ((HttpServletRequest)request).getContextPath(); //  "/pro10"
		//2.클라이언트가 요청한 전체 URL주소 중에서 URI 주소 얻어 저장
		String pathInfo =   ((HttpServletRequest)request).getRequestURI();  //   "/pro10/login"
		//3.클라이언트가 요청한 URI 주소의 실제 경로 얻어 저장
		String realPath = request.getRealPath(pathInfo);
		
		String mesg = "ContextPath : " + contextPath + "\n URI 정보 : " + pathInfo + "\n 물리적 URI 정보 : " + realPath;
		System.out.println(mesg);
		
		//4. 요청 필터에서 요청 처리 전의 작업 시간 구하기 
		long begin = System.currentTimeMillis();
		
		
		//작업 : 1 1000번 반복해서 출력
		for(int i=0;    i<1000;  i++) {
			System.out.println("1");
		}
		
//----------------------------------------------------------------------------------		
// 다음 에 작성된 필터 역할을 하는 클래스 또는 서블릿(LoginTest)으로 넘기는 작업을 요청하기 위한 메소드 호출!
		chain.doFilter(request, response);
//----------------------------------------------------------------------------------		
		
		
		//4.1. 응답 필터에서 요청 처리후 의 작업 시간 구하기 
		long end = System.currentTimeMillis();

		//4.2. 작업 요청전과 후의 시간 차이를 구해 작업 수행시간을 구하기
		System.out.println("작업 수행시간 : " + (end - begin) + "ms");
		
	
		
	}

	@Override
	public void destroy() {
		System.out.println("destory 메소드 호출 됨");
	}
	

}






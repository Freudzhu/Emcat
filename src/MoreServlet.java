import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.ParameterMap;

import com.emcat.connector.HttpRequest;


public class MoreServlet implements Servlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5280314002871785249L;


	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("load the Servlet");
		res.getWriter().println("HTTP/1.1 200 OK");
		res.getWriter().println("Content-Type:text/html;charset=ISO-8859-1");
		res.getWriter().println("Content-length:16");
		res.getWriter().println();
		res.getWriter().println("Hello world</br>");
		HttpRequest hsr = (HttpRequest)req;
		res.getWriter().println(Thread.currentThread().getName()+"</br>");
		res.getWriter().flush();
		
		
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}


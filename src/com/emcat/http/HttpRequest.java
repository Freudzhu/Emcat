package com.emcat.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.catalina.util.ParameterMap;
import org.apache.catalina.util.RequestUtil;

public class HttpRequest implements HttpServletRequest{
	
	
	private String uri;
	private HttpRequest request;
	private String contentType;
	private int contentLength;
	private String method;
	private String protocol;
	private String queryString;
	private String requestURI;
	private String serverName;
	private int serverPort;
	private boolean requestedSessionCookie;
	private String requestedSessionId;
	private boolean requestedSessionURL;
	
	//collect the heads(key,value)
	protected HashMap headers = new HashMap();
	
	//collect the cookies
	protected ArrayList cookies = new ArrayList();
	
	//collect the Parameter
	protected ParameterMap parameters = null;
	
	/**
	   * Have the parameters for this request been parsed yet?
	 */
	protected boolean parsed = false;
	
	 public void addHeader(String name, String value) {
		  name = name.toLowerCase();
		  synchronized (headers) {
			  ArrayList values = (ArrayList) headers.get(name);
			  if (values == null) {
			      values = new ArrayList();
			      headers.put(name, values);
			  }
			  values.add(value);
		  }
	 }
	 
	 public void addCookie(Cookie cookie) {
		  synchronized (cookies) {
		     cookies.add(cookie);
		  }
	 }

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public AsyncContext getAsyncContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<Locale> getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		parseParameters();
		
		String values[] = (String[]) parameters.get(name);
	    if (values != null)
	      return (values[0]);
	    else
	      return (null);
		
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterValues(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRealPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AsyncContext startAsync() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authenticate(HttpServletResponse arg0) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		synchronized (cookies) {
		    if (cookies.size() < 1)
		        return (null);
		    Cookie results[] = new Cookie[cookies.size()];
		    return ((Cookie[]) cookies.toArray(results));
		}
	}

	@Override
	public long getDateHeader(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		name = name.toLowerCase();
	    synchronized (headers) {
	      ArrayList values = (ArrayList) headers.get(name);
	      if (values != null)
	        return ((String) values.get(0));
	      else
	        return null;
	    }
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getHeaders(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIntHeader(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Part getPart(String arg0) throws IOException, IllegalStateException,
			ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Part> getParts() throws IOException,
			IllegalStateException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return queryString;
	}

	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession(boolean arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUserInRole(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void login(String arg0, String arg1) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout() throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public boolean isRequestedSessionCookie() {
		return requestedSessionCookie;
	}

	public void setRequestedSessionCookie(boolean requestedSessionCookie) {
		this.requestedSessionCookie = requestedSessionCookie;
	}

	public boolean isRequestedSessionURL() {
		return requestedSessionURL;
	}

	public void setRequestedSessionURL(boolean requestedSessionURL) {
		this.requestedSessionURL = requestedSessionURL;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public void setRequestedSessionId(String requestedSessionId) {
		this.requestedSessionId = requestedSessionId;
	}
	/**
	   * Parse the parameters of this request, if it has not already occurred.
	   * If parameters are present in both the query string and the request
	   * content, they are merged.
	   */
	  protected void parseParameters() {
	    if (parsed)
	      return;
	    ParameterMap results = parameters;
	    if (results == null)
	      results = new ParameterMap();
	    results.setLocked(false);
	    String encoding = getCharacterEncoding();
	    if (encoding == null)
	      encoding = "ISO-8859-1";

	    // Parse any parameters specified in the query string
	    String queryString = getQueryString();
	    try {
	      RequestUtil.parseParameters(results, queryString, encoding);
	    }
	    catch (UnsupportedEncodingException e) {
	      ;
	    }

	    // Parse any parameters specified in the input stream
	    String contentType = getContentType();
	    if (contentType == null)
	      contentType = "";
	    int semicolon = contentType.indexOf(';');
	    if (semicolon >= 0) {
	      contentType = contentType.substring(0, semicolon).trim();
	    }
	    else {
	      contentType = contentType.trim();
	    }
	    if ("POST".equals(getMethod()) && (getContentLength() > 0)
	      && "application/x-www-form-urlencoded".equals(contentType)) {
	      try {
	        int max = getContentLength();
	        int len = 0;
	        byte buf[] = new byte[getContentLength()];
	        ServletInputStream is = getInputStream();
	        while (len < max) {
	          int next = is.read(buf, len, max - len);
	          if (next < 0 ) {
	            break;
	          }
	          len += next;
	        }
	        is.close();
	        if (len < max) {
	          throw new RuntimeException("Content length mismatch");
	        }
	        RequestUtil.parseParameters(results, buf, encoding);
	      }
	      catch (UnsupportedEncodingException ue) {
	        ;
	      }
	      catch (IOException e) {
	        throw new RuntimeException("Content read fail");
	      }
	    }

	    // Store the final results
	    results.setLocked(true);
	    parsed = true;
	    parameters = results;
	  }

}

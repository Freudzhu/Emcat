package com.emcat.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;




import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import com.emcat.commom.Constants;

public class Response implements ServletResponse{
	
	private Request request;
	private OutputStream outputStream;
	
	public void setRequest(Request request) {
		this.request = request;
	}
	public Request getRequest() {
		return request;
	}
	public Response(OutputStream outputStream){
		this.outputStream = outputStream;
	}

	public ServletOutputStream getOutputStream() {
		return null;
	}

	public void sendStaticResource(){
		String url = request.getUri();
		File file = new File(Constants.WEB_ROOT,url);
		if(file.exists()){
			//file exist,then write the http header into the Stream
			PrintWriter pw = new PrintWriter(outputStream,true);
			pw.println("HTTP/1.1 200 OK");
			pw.println("Content-Type: text/html;charset=ISO-8859-1");
			pw.println();
	
			try {
				FileReader bf = new FileReader(file);
				char[] tmp = new char[1024];
				while (bf.read(tmp)!=-1) {
					pw.write(tmp, 0, tmp.length);
					
				}
				pw.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(outputStream!=null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else {
			StringBuffer errMsg = new StringBuffer();
			errMsg.append("HTTP/1.1 404 Not Found\r\n");
			errMsg.append("Content-Type: text/html;charset=ISO-8859-1\r\n");
			errMsg.append("\r\n");
			errMsg.append("File Not find!");
			try {
				outputStream.write(errMsg.toString().getBytes());
				outputStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return new PrintWriter(outputStream,true);
	}
	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setContentType(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub
		
	}


}

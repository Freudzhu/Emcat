package com.emcat.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.emcat.commom.Constants;

public class FileLogger extends LoggerBase{
	
	
	String filePath = Constants.WEB_ROOT;
	
	String preFix ;
	
	Boolean timeStampEnable;
	
	String date;
	
	File file;
	
	FileWriter writer;
	
	
	public FileLogger(Boolean timeEnable,String prefix){
		this.timeStampEnable = timeEnable;
		this.preFix = prefix;
	}
	public FileLogger(Boolean timeEnable){
		this(timeEnable, "");
	}

	@Override
	public void log(String msg) {
		// TODO Auto-generated method stub
		 Date dt = new Date(); 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		 String newDate = sdf.format(dt);
		 if(!newDate.equals(date)){
			 date = newDate;
			 close();
			 open();
		 }
		 BufferedWriter bufferedWriter = new BufferedWriter(writer);
		 try {
			 String timeStr = time.format(dt);
			 bufferedWriter.write(timeStr+":"+msg);
			 bufferedWriter.newLine();
			 bufferedWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void open(){
		String fileName = null;
		if(timeStampEnable){
			fileName = filePath +  File.separator + "log"  + preFix + date + ".txt";
		}else{
			fileName = filePath +  File.separator + "log"  + preFix  + ".txt";
		}
		file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			writer = new FileWriter(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void close(){
		if(writer != null){
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

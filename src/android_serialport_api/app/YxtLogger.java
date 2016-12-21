/** 
 * Project Name:yxt_android 
 * File Name:YxtLogger.java 
 * Package Name:com.yxt.app.utils 
 * Date:2016年9月19日上午11:50:14 
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
/*** */  
  
package android_serialport_api.app;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import android.os.Environment;
import android.util.Log;
import de.mindpipe.android.logging.log4j.LogConfigurator;

/** 
 * ClassName: YxtLogger <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年9月19日 上午11:50:14 <br/> 
 * 
 * @author lyh 
 * @version  
 */
public class YxtLogger {
	/** 
	 * configLog:(). <br/> 
	 * TODO().<br/> 
	 * log4j配置
	 * @author lyh  
	 */  
	public static void configLog() {
		
		try {
			LogConfigurator logConfigurator = new LogConfigurator();
			//Log.e(Environment.getExternalStorageState()+"::" + Environment.getExternalStorageDirectory(), "log地址");
			
			logConfigurator.setFileName(Environment.getExternalStorageDirectory() + File.separator +"yxtlogs"+  File.separator +"android_log4j.log");
			// logConfigurator.setFileName("/mnt/sdcard/" +
			// "android_log4j.log");
			
			// Set the root log level
			logConfigurator.setRootLevel(Level.ALL);
			 // 设置日志输出级别
	        logConfigurator.setLevel("org.apache", Level.ALL);
			 //设置 输出到日志文件的文字格式 默认 %d %-5p [%c{2}]-[%L] %m%n
	        logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
	        //设置输出到控制台的文字格式 默认%m%n
	        logConfigurator.setLogCatPattern("%m%n");
	        //设置总文件大小
	        logConfigurator.setMaxFileSize(1024 * 1024 * 5);
	        //设置最大产生的文件个数
	        logConfigurator.setMaxBackupSize(1);
	        
	        //设置所有消息是否被立刻输出 默认为true,false 不输出
	        logConfigurator.setImmediateFlush(true);
	        //是否本地控制台打印输出 默认为true ，false不输出
	        logConfigurator.setUseLogCatAppender(true);
	        //设置是否启用文件附加,默认为true。false为覆盖文件
	        logConfigurator.setUseFileAppender(true);
	        //设置是否重置配置文件，默认为true
	        logConfigurator.setResetConfiguration(true);
	        //是否显示内部初始化日志,默认为false
	        logConfigurator.setInternalDebugging(false);
	        logConfigurator.configure();
		//	gLogger = Logger.getLogger(this.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * writeLog:(). <br/> 
	 * TODO().<br/> 
	 * 为了统一性要这样调用
	 * @author lyh 
	 * @param gLogger
	 * @param msg
	 * @param t
	 */
	public static void writeLog(Logger gLogger,Object msg, Throwable t) {
		if (gLogger != null) {
			gLogger.error(msg, t);
		}
	}

	/** 
	 * writeLog:(). <br/> 
	 * TODO().<br/> 
	 * 为了统一性
	 * @author lyh 
	 * @param gLogger
	 * @param msg 
	 */  
	public static void writeLog(Logger gLogger,Object msg) {
		if (gLogger != null) {
			gLogger.error(msg);
		}
	}
	
}
  
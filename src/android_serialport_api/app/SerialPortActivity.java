package android_serialport_api.app;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android_serialport_api.CardListener;
import android_serialport_api.SerialPortControl;

public class SerialPortActivity extends Activity implements CardListener{
	private Logger logger;
	private SerialPortControl spControl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serial_port);
		YxtLogger.configLog();
		logger = Logger.getLogger(SerialPortActivity.class);
		spControl = new SerialPortControl("/dev/ttyS2", 9600, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.serial_port, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (spControl != null){
			spControl.onDestroy();
		}
		
	}

	@Override
	public void receiveCard(byte[] cardData) {
		// TODO Auto-generated method stub
		YxtLogger.writeLog(logger, "进入打卡阶段::");
		String cardNum = null;
		try {
			cardNum = new String(cardData,"UTF-8");
			cardNum = cardNum.trim();//去掉首尾的空格,特别注意,有的卡号是16进制的,请自行转成10进制的
		} catch (UnsupportedEncodingException e) {
			YxtLogger.writeLog(logger,"转换错误",e);
		}
			
		YxtLogger.writeLog(logger, "卡号::"+cardNum);
	}
	
	/** 
	 * getAndroidMac:(). <br/> 
	 * TODO().<br/> 
	 * 获取mac机器码地址
	 * @author lyh 
	 * @return 
	 */  
	private String getAndroidMac() 
	  {
	      String macSerial = null;
	      String str = "";
	  
	      try 
	      {
	          Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
	          InputStreamReader ir = new InputStreamReader(pp.getInputStream());
	         LineNumberReader input = new LineNumberReader(ir);
	
	         for (; null != str;) 
	         {
	            str = input.readLine();
	             if (str != null)
	             {
	                 macSerial = str.replace(":", "").trim();// 去空格
	                 break;
	             }
	         }
	     } catch (IOException ex) {
	         // 赋予默认值
	         ex.printStackTrace();
	     }
	     return macSerial;
	 }
}

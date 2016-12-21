
package android_serialport_api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import android_serialport_api.app.YxtLogger;


/**
 * ClassName:SerialPortCom <br/>
 * Function: TODO (). <br/>
 * Reason: TODO (). <br/>
 * Date: 2016年12月18日 下午3:40:23 <br/>
 * 
 * @author lyh
 * @version
 * @see
 */
public abstract class SerialPortAbstract {
	protected SerialPort mSerialPort;
	protected OutputStream mOutputStream;
	private InputStream mInputStream;
	private ReadThread mReadThread;
	protected Logger logger;
	
	private class ReadThread extends Thread {
		byte[] buffer = new byte[512];
		
		@Override
		public void run() {
			
			while (true) {
				if (isInterrupted())
					return;
				int size = 0;
				try {
					
					if (mInputStream == null)
						return;
					size = mInputStream.read(buffer);
					if (size > 0) {
						YxtLogger.writeLog(logger, "数据来了");
						onDataReceived(buffer, size);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
					return;
				} finally {
					try {
						Thread.sleep(100L);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * comReadFile:(). <br/>
	 * TODO().<br/>
	 * 
	 * @author lyh
	 * @param serialPortFile//"/dev/ttyS2"
	 * @param iBaudRate
	 */
	public void readFile(String serialPortFile, int iBaudRate) {
		try {
			logger = Logger.getLogger(SerialPort.class);
			mSerialPort = new SerialPort(new File(serialPortFile), iBaudRate, 0);
			mOutputStream = mSerialPort.getOutputStream();
			mInputStream = mSerialPort.getInputStream();
			mReadThread = new ReadThread();
			mReadThread.start();
		} catch (Exception e) {
			YxtLogger.writeLog(logger, "有问题呀" + e);
			e.printStackTrace();
		}
	}
	
	protected abstract void onDataReceived(final byte[] buffer, final int size);
	
	public void onDestroy() {
		if (mReadThread != null) {
			mReadThread.interrupt();
		}
		if (mSerialPort != null) {
			mSerialPort.close();
			mSerialPort = null;
		}
		mSerialPort = null;
		
	}
	
}

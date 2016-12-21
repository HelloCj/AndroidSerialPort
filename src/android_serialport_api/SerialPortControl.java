
package android_serialport_api;

import java.util.LinkedList;
import java.util.Queue;

import android_serialport_api.app.YxtLogger;


/** 
 * ClassName:ComControl <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年12月18日 下午4:12:28 <br/> 
 * @author   lyh 
 * @version   
 * @see       
 */
public class SerialPortControl extends SerialPortAbstract implements Runnable {
	
	private Queue<CardNum> queueList = new LinkedList<CardNum>();
	private CardListener cListener;
	private boolean running = false;
	private Thread tThead  = new Thread(this);
	public SerialPortControl(String serialPortFile,int iBaudRate,CardListener _cListener){
		readFile(serialPortFile, iBaudRate);
		cListener = _cListener;
		running = true;
		tThead.start();
	}
	
	/** (non-Javadoc)
	 * @see android_serialport_api.SerialPortAbstract#onDataReceived(byte[], int)
	 */
	@Override
	public void onDataReceived(byte[] buffer, int size) {
		// TODO Auto-generated method stubnew CardNum(buffer,size)
		CardNum cn = new CardNum(buffer,size);
		
		queueList.add(cn);
		
	}

	/** (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running){
			CardNum cn = queueList.poll();
			YxtLogger.writeLog(logger, "每次时间"+cn);
			if (cn != null){
				YxtLogger.writeLog(logger, "有数据进来!!!");
				cListener.receiveCard(cn.getReceiveArray());
			}
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//停止串口处理线程
	public void onDestroy(){
		running = false;
		super.onDestroy();
	}

}
  
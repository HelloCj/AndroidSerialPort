
package android_serialport_api;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:CardNum <br/>
 * Function: TODO (). <br/>
 * Reason: TODO (). <br/>
 * Date: 2016年12月18日 下午4:15:53 <br/>
 * 
 * @author lyh
 * @version
 * @see
 */
public class CardNum {
	private byte receiveArray[] = new byte[0];
	private String receiveTime;
	
	public CardNum(byte cardBuff[], int size) {
		receiveArray = new byte[size];
		for (int i = 0;; i++) {
			if (i >= size) {
				this.receiveTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
				return;
			}
			receiveArray[i] = cardBuff[i];
			
		}
	}
	
	/**
	 * receiveArray.
	 * 
	 * @return the receiveArray
	 */
	public byte[] getReceiveArray() {
		return receiveArray;
	}
	
	/**
	 * receiveArray.
	 * 
	 * @param receiveArray the receiveArray to set
	 */
	public void setReceiveArray(byte[] receiveArray) {
		this.receiveArray = receiveArray;
	}
	
	/**
	 * receiveTime.
	 * 
	 * @return the receiveTime
	 */
	public String getReceiveTime() {
		return receiveTime;
	}
	
	/**
	 * receiveTime.
	 * 
	 * @param receiveTime the receiveTime to set
	 */
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
}

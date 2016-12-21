
package android_serialport_api;  
/** 
 * ClassName:CardListener <br/> 
 * Function: TODO (处理卡号监听). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年12月18日 下午4:38:59 <br/> 
 * @author   lyh 
 * @version   
 * @see       
 */
public interface CardListener {
	void receiveCard(byte cardData[]);
}
  
package semi.kh.common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import semi.kh.member.model.service.MemberService;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */
public class ExampleSend {
  public static void main(String memberId, String phone) {
	
    String api_key = "NCS7XCXBNSGR2UBW";
    String api_secret = "XPXUTKUNESHDBBVOHV3Z0XBHIMVJMRGV";
    Message coolsms = new Message(api_key, api_secret);
   
    double ranNum = Math.random();
    int password = (int)(ranNum *999999) +100000;
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", phone);
    params.put("from", "01085574396");
    params.put("type", "SMS");
    params.put("text", "Temporary Number : "+password);
    params.put("app_version", "test app 1.2"); // application name and version
    
    
    String value = getSha512(String.valueOf(password));
    
    int result = new MemberService().temporaryPwd(memberId,value);
    if(result>0) {
    	
    	try {
    		JSONObject obj = (JSONObject) coolsms.send(params);
    		System.out.println(obj.toString());
    	} catch (CoolsmsException e) {
    		System.out.println(e.getMessage());
    		System.out.println(e.getCode());
    	}
    	
    }
    
   
  }
  private static String getSha512(String password) {
		String encPwd = null;
		MessageDigest md = null;
		System.out.println(password);
		//1.암호화 객체 생성 : sha-512 
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2.사용자가 입력한 password를 바이트배열로 변환
		
		byte[] bytes = password.getBytes(Charset.forName("utf-8"));
//		byte[] bytes2 = password_new.getBytes(Charset.forName("utf-8"));
		
		//3.md 객체에 바이트 배열 전달해서 갱신
		md.update(bytes);
//		md.update(bytes2);
		
		//4.암호화 처리 
		byte[] encBytes = md.digest();
		
		//5.Base64인코더를 사용해서 암호화된 바이트 배열을 문자열로 변환 
		encPwd = Base64.getEncoder().encodeToString(encBytes);
		
		
		return encPwd;
	}

  
  
}

package semi.kh.common.wrapper;

import java.nio.charset.Charset;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

//생성할때 수퍼클래스를 상속받을것 : httpservletrequestwrapper?

public class EncryptWrapper extends HttpServletRequestWrapper {

	//부모클래스에 기본생성자가 없으므로, parameter생성자를 명시적으로 작성
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String key) {
		String value = "";
		if(key != null && 
				("password".equals(key) || "password_new".equals(key))) {
				value = getSha512(super.getParameter(key));
				
			}
			else {
				value = super.getParameter(key);
			}
			
			return value;
	}
	
	private String getSha512(String password) {
		String encPwd = null;
		MessageDigest md = null;
		System.out.println(password);
		
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
	
		
		byte[] bytes = password.getBytes(Charset.forName("utf-8"));

		md.update(bytes);

		byte[] encBytes = md.digest();
		
		encPwd = Base64.getEncoder().encodeToString(encBytes);
		
		
		return encPwd;
	}


}

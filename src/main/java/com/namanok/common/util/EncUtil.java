package com.namanok.common.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.namanok.common.exception.ErrorEnum;
import com.namanok.common.exception.BizException;

public class EncUtil {

	private static final String SECRET_KEY = "jfirlgofpqovkhlsyenfkvyategkderk"; // 암/복호화에 사용되는 key 32byte == 256bit
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String IV = "jfieoghlricjaher";	//16byte(128bit)
	
	/**
	 * 암호화
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String aes256enc(String str){

		String returnStr = "";
		
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));

			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			returnStr = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
		} catch(Exception e) {
			//시스템 오류가 발생했습니다. 잠시후 다시 시도해 주세요.
			throw new BizException(ErrorEnum.SYS9999);
		}

		return returnStr;
	}
	
	/**
	 * 복호화
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String aes256dec(String str){
		
		String returnStr = "";
		
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
	
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			returnStr = new String(cipher.doFinal(Base64.getDecoder().decode(str)), "UTF-8");
		} catch(Exception e) {
			//시스템 오류가 발생했습니다. 잠시후 다시 시도해 주세요.
			throw new BizException(ErrorEnum.SYS9999);
		}
		
		return returnStr;
	}
}

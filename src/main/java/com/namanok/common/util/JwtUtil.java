package com.namanok.common.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;

public class JwtUtil {
	
	/**
	 * JWT Token »ý¼º
	 * 
	 * @param userId
	 * @param secretKey
	 * @return
	 */
	public static String createJWT(String userId, String secretKey) {

		if(userId == null || "".equals(userId) || secretKey == null || "".equals(secretKey)) {
			return null;
		}
		
		String jwtToken = JWT.create()
			.withSubject("myToken")
			.withExpiresAt(new Date(System.currentTimeMillis()+(1000 * 60 * 10)))
			.withClaim("userId", userId)
			.sign(Algorithm.HMAC512(secretKey));
		
		return jwtToken;
	}
	
	/**
	 * JWT Token verify ¹× userId È¹µæ
	 * 
	 * @param jwtHeader
	 * @param secretKey
	 * @return
	 * @throws TokenExpiredException
	 */
	public static String jwtVerifyAndGetUserId(String jwtHeader, String secretKey) throws TokenExpiredException{

		if(jwtHeader == null || "".equals(jwtHeader) || secretKey == null || "".equals(secretKey)) {
			return null;
		}
		
		String userId = JWT.require(Algorithm.HMAC512(secretKey)).build()
			.verify(jwtHeader)
			.getClaim("userId").asString();
		
		return userId;
	}
}

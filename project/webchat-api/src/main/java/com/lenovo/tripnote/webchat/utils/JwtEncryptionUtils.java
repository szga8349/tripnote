package com.lenovo.tripnote.webchat.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtEncryptionUtils {
	public static String enString(String rawString){
		String jwtToken = Jwts.builder().setSubject(rawString).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		return jwtToken;
	}
    
}

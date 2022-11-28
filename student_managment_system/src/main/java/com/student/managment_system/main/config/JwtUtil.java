package com.student.managment_system.main.config;

import java.security.Key;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.student.managment_system.main.model.Student;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final static String jwtSecret="tpF++cRaY/rdKDEymYBL0abeKGXjoXS+neGDe6LUc60=";
	private final static SecretKey key=Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

	// To generate secretkey 
	//	Key key=Keys.secretKeyFor(SignatureAlgorithm.HS256);
	//	  public JwtUtil() {
	//			System.out.println(Encoders.BASE64.encode(key.getEncoded()));
	//		}

    /*
     * 
     * 
     *  generateToken method is used for generating token 
     */

	public String generateToken(Student student) {
		return Jwts.builder()
				.setSubject(student.getId()+","+student.getEmail())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+(60*60*1000)))
				.signWith(key)
				.compact();

	}
	
	/*
	 * 
	 *  getUserId method is used for fetching the userId from the token
	 * 
	 */

	public String getUserId(String token) {
		Claims claims=Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject().split(",")[0];


	}

	/*
	 * 
	 * getUsername method is used for fetching the username  from the token
	 * 
	 */
	public String getUsername(String token) {
		Claims claims=Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject().split(",")[1];
	}

	/*
	 * 
	 * getExperationDate method is used for finding the token ExperationDate
	 * 
	 */
	
	public Date  getExperationDate(String token) {
		Claims claims=Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getExpiration();


	}
	
	/*
	 * 
	 * validate method is used for  validating the token 
	 * 
	 */

	public boolean validate(String token) {

		Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token);
		return true;

	}
	
	



}

package com.bridgelabz.fundoo.utility;

import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Component
public class JWTOperations {
	private static final String SECRET = System.getenv("secret");

	public String jwtToken(String email) {
		String token = null;
		try {
			token = JWT.create().withClaim("email", email).sign(Algorithm.HMAC256(SECRET));
		} catch (IllegalArgumentException | JWTCreationException e) {
			e.printStackTrace();
		}
		return token;
	}

	public String parseJWT(String jwt) {
		String email = null;
		if (jwt != null) {
			email = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(jwt).getClaim("email").asString();
		}
		return email;

	}

}
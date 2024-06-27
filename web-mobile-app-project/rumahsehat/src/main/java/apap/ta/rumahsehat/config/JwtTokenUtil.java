package apap.ta.rumahsehat.config;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenUtil implements Serializable {

	@Value("${jwttoken.secret}")
	private String jwtTokenSecret;
	@Value("${jwttoken.expiration}")
	private long jwtTokenExpiration;
	
	public String generateJwtToken(Authentication authentication) {
		var userPrincipal = (User) authentication.getPrincipal();
		return Jwts.builder()
				   .setSubject(userPrincipal.getUsername())
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + jwtTokenExpiration))
				   .signWith(SignatureAlgorithm.HS512, jwtTokenSecret)
				   .compact();
	}

	public String refreshJwtToken() {
		return Jwts.builder()
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + 1))
				   .signWith(SignatureAlgorithm.HS512, jwtTokenSecret)
				   .compact();
	}
	
	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(jwtTokenSecret)
				.parseClaimsJws(token);
			return true;
		}catch(UnsupportedJwtException exp) {
			log.error(String.format("claimsJws argument does not represent Claims JWS %s", exp.getMessage()));
		}catch(MalformedJwtException exp) {
			log.error(String.format("claimsJws string is not a valid JWS %s", exp.getMessage()));
		}catch(SignatureException exp) {
			log.error(String.format("claimsJws JWS signature validation failed %s", exp.getMessage()));
		}catch(ExpiredJwtException exp) {
			log.error(String.format("Claims has an expiration time before the method is invoked %s", exp.getMessage()));
		}catch(IllegalArgumentException exp) {
			log.error(String.format("claimsJws string is null or empty or only whitespace %s", exp.getMessage()));
		}
		return false;
	}
	
	public String getUserNameFromJwtToken(String token) {
		var claims =Jwts.parser()
				   .setSigningKey(jwtTokenSecret)
				   .parseClaimsJws(token)
				   .getBody();
		return claims.getSubject();
		
	}
}
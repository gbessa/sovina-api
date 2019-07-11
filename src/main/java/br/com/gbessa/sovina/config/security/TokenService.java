package br.com.gbessa.sovina.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.gbessa.sovina.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${api.jwt.expiration}")
	private String expiration;
	
	@Value("${api.jwt.secretKey}")
	private String secretKey;
	
	public String generateToken(Authentication authentication) {
		User userLogged = (User) authentication.getPrincipal();
		return generateToken(userLogged);
		
//		Optional<T> principal = (Optional<T>) authentication.getPrincipal();
//		if (principal instanceof Optional<User>){
//		    Optional<User> userLogged = (Optional<User>) principal;		
//		    return generateToken(userLogged.get());
//		} else {
//			throw new UsernameNotFoundException(null);
//		}
	}
	
	public String generateToken(User user) {
		Date today = new Date();
		
		return Jwts.builder()
				.setIssuer("Sovina API")
				.setSubject(user.getId().toString())
				.setIssuedAt(today)
				.setExpiration(new Date(today.getTime() + Long.parseLong(expiration)))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	
	
}

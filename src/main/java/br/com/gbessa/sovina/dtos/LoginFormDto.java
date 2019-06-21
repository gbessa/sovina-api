package br.com.gbessa.sovina.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

@Data public class LoginFormDto {
	
	private String email;
	private String password;
	
	public UsernamePasswordAuthenticationToken buildCreds() {
		return new UsernamePasswordAuthenticationToken(this.email, this.password);
	}

}

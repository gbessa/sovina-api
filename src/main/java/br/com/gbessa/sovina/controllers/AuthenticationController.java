package br.com.gbessa.sovina.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gbessa.sovina.config.security.TokenService;
import br.com.gbessa.sovina.config.security.UserService;
import br.com.gbessa.sovina.dtos.LoginFormDto;
import br.com.gbessa.sovina.dtos.TokenDto;
import br.com.gbessa.sovina.models.User;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginFormDto loginForm) {
		UsernamePasswordAuthenticationToken creds = loginForm.buildCreds();
		
		try {
			Authentication authentication = authenticationManager.authenticate(creds);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PostMapping(value="/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response, Authentication authentication) {
		
		User user = UserService.authenticated();
		String token = tokenService.generateToken(user);
		
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorizartion");
		return ResponseEntity.noContent().build();
	}
	
}

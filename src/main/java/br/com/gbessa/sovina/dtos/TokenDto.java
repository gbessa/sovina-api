package br.com.gbessa.sovina.dtos;

import lombok.Getter;

@Getter
public class TokenDto {

	private String token;
	private String type;

	public TokenDto(String token, String type) {
		this.token = token;
		this.type = type; 
	}

}

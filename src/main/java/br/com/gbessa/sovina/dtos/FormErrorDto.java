package br.com.gbessa.sovina.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FormErrorDto {

	private String field;
	private String error; 
	
}

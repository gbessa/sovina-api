package br.com.gbessa.sovina.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.gbessa.sovina.models.Store;
import lombok.Data;

@Data public class StoreFormDto {

	@NotNull @NotEmpty @Length(min=3, max=100)
	private String name;
	@NotNull @NotEmpty @Length(min=2, max=100)
	private String location;
	private String plusCode;
	
	public static Store fromDto(StoreFormDto storeForm) {
		return new Store(null, storeForm.getName(), storeForm.getLocation(), storeForm.getPlusCode());
	}
	
}

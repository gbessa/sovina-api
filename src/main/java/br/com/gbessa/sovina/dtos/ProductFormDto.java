package br.com.gbessa.sovina.dtos;

import javax.validation.Valid;

import br.com.gbessa.sovina.models.Product;
import lombok.Data;

@Data public class ProductFormDto {

	private Long id;
	private String name;
	private String brand;
	private String scannedCode;
	
	public static Product fromDto(@Valid ProductFormDto productForm) {
		return new Product(null, productForm.getName(), productForm.getBrand(), productForm.getScannedCode());
	}
	
}

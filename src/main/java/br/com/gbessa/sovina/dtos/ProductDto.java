package br.com.gbessa.sovina.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gbessa.sovina.models.Product;
import lombok.Getter;

@Getter
public class ProductDto {

	private Long id;
	private String name;
	private String brand;
	private String scannedCode;

	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.brand = product.getBrand();
		this.scannedCode = product.getScannedCode();
	}
	
	public static List<ProductDto> toDto(List<Product> products) {
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}

	public static ProductDto toDto(Product product) {
		return new ProductDto(product);
	}
	
}

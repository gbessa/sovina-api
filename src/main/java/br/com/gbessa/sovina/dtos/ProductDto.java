package br.com.gbessa.sovina.dtos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.gbessa.sovina.models.Product;
import lombok.Getter;

@Getter
public class ProductDto {

	private Long id;
	private String name;
	private String brand;
	private String scannedCode;
	private Boolean favorited;

	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.brand = product.getBrand();
		this.scannedCode = product.getScannedCode();
		this.favorited = product.getFavorited();
	}
	
	public static List<ProductDto> toDto(List<Product> products) {
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}

	public static Page<ProductDto> toDto(Page<Product> products) {
		return products.map(ProductDto::new);
	}

	public static ProductDto toDto(Product product) {
		return new ProductDto(product);
	}

}

package br.com.gbessa.sovina.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.gbessa.sovina.models.Price;
import br.com.gbessa.sovina.models.Product;
import br.com.gbessa.sovina.models.Store;
import lombok.Getter;

@Getter
public class PriceDto {

	private Long id;
	private Double price;
	private LocalDate dateFind;
	private String productName;
	private String storeName;

	public PriceDto(Price price) {
		this.id = price.getId();
		this.price = price.getPrice();
		this.dateFind = price.getDateFind();
		this.productName = price.getProduct().getName() + " - " + price.getProduct().getBrand();
		this.storeName = price.getStore().getName() + " - " + price.getStore().getLocation();
	}
	
	public static List<PriceDto> toDto(List<Price> prices) {
		return prices.stream().map(PriceDto::new).collect(Collectors.toList());
	}

	public static PriceDto toDto(Price price) {
		return new PriceDto(price);
	}
	
}

package br.com.gbessa.sovina.dtos;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.gbessa.sovina.models.Price;
import br.com.gbessa.sovina.models.Product;
import br.com.gbessa.sovina.models.Store;
import lombok.Data;

@Data public class PriceFormDto {

	@NotNull
	private Double price;
	@NotNull
	private LocalDate dateFind;
	@NotNull
	private Long productId;
	@NotNull
	private Long storeId;
	
	public static Price fromDto(@Valid PriceFormDto priceForm, Store store, Product product ) {
		return new Price(null, product, store, priceForm.getPrice(), priceForm.getDateFind());
	}
	
	
	
}

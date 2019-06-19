package br.com.gbessa.sovina.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gbessa.sovina.dtos.PriceDto;
import br.com.gbessa.sovina.models.Price;
import br.com.gbessa.sovina.repositories.PriceRepository;

@RestController
@RequestMapping("/prices")
public class PriceController {

	@Autowired
	private PriceRepository priceRepository;
	
	@GetMapping
	public List<PriceDto> list(String productName) {
		List<Price> prices;
		if (productName == null) {
			prices = priceRepository.findAll();
		} else {
			prices = priceRepository.findByProductName(productName);
		}		
		return PriceDto.toDto(prices);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PriceDto> detail(@PathVariable Long id) {
		Optional<Price> price = priceRepository.findById(id);
		return price.isPresent() ? ResponseEntity.ok(PriceDto.toDto(price.get())) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		priceRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}

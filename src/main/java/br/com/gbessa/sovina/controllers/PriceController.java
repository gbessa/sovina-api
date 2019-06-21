package br.com.gbessa.sovina.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gbessa.sovina.dtos.PriceDto;
import br.com.gbessa.sovina.dtos.PriceFormDto;
import br.com.gbessa.sovina.models.Price;
import br.com.gbessa.sovina.models.Product;
import br.com.gbessa.sovina.models.Store;
import br.com.gbessa.sovina.repositories.PriceRepository;
import br.com.gbessa.sovina.repositories.ProductRepository;
import br.com.gbessa.sovina.repositories.StoreRepository;

@RestController
@RequestMapping("/prices")
public class PriceController {

	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public List<PriceDto> list(String productName) {
		System.out.println("Caching: allPricesList");
		List<Price> prices;
		if (productName == null) {
			prices = priceRepository.findAll();
		} else {
			prices = priceRepository.findByProductName(productName);
		}		
		return PriceDto.toDto(prices);
	}
	
	@GetMapping("/cached")
	@Cacheable(value="CacheAllPricesList")
	public List<PriceDto> listAllCached(String productName) {
		List<Price> prices;
		prices = priceRepository.findAll();
		return PriceDto.toDto(prices);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PriceDto> detail(@PathVariable Long id) {
		Optional<Price> price = priceRepository.findById(id);
		return price.isPresent() ? ResponseEntity.ok(PriceDto.toDto(price.get())) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="CacheAllPricesList", allEntries=true)
	public ResponseEntity<PriceDto> insert(@RequestBody @Valid PriceFormDto priceForm, UriComponentsBuilder uriBuilder) {
		Store store = storeRepository.getOne(priceForm.getStoreId());
		Product product = productRepository.getOne(priceForm.getProductId());
		Price price = PriceFormDto.fromDto(priceForm, store, product);
		priceRepository.save(price);
		
		URI uri = uriBuilder.path("/prices/{id}").buildAndExpand(price.getId()).toUri();
		return ResponseEntity.created(uri).body(new PriceDto(price));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="CacheAllPricesList", allEntries=true)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		priceRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}

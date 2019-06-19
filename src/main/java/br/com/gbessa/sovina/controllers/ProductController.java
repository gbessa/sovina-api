package br.com.gbessa.sovina.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gbessa.sovina.dtos.PriceDto;
import br.com.gbessa.sovina.dtos.ProductDto;
import br.com.gbessa.sovina.dtos.ProductFormDto;
import br.com.gbessa.sovina.models.Product;
import br.com.gbessa.sovina.repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping
	public List<ProductDto> list(){
		return ProductDto.toDto(productRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> detail(@PathVariable Long id){
		Optional<Product> product = productRepository.findById(id);
		return product.isPresent() ? ResponseEntity.ok(ProductDto.toDto(product.get())) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody @Valid ProductFormDto productForm) {
		Product product = productRepository.getOne(id);
		product.setName(productForm.getName());
		product.setBrand(productForm.getBrand());
		product.setScannedCode(productForm.getScannedCode());
		
		return ResponseEntity.ok(ProductDto.toDto(product));
	}
	
}

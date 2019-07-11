package br.com.gbessa.sovina.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gbessa.sovina.dtos.ProductDto;
import br.com.gbessa.sovina.dtos.ProductFormDto;
import br.com.gbessa.sovina.models.Product;
import br.com.gbessa.sovina.repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/all")
	public List<ProductDto> listAll(){
		return ProductDto.toDto(productRepository.findAll());
	}
	
	@GetMapping
	public Page<ProductDto> listPageable(Pageable pageable){
		Page<Product> products = productRepository.findAll(pageable);
		return ProductDto.toDto(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> detail(@PathVariable Long id){
		Optional<Product> product = productRepository.findById(id);
		return product.isPresent() ? ResponseEntity.ok(ProductDto.toDto(product.get())) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> insert(@RequestBody @Valid ProductFormDto productForm, UriComponentsBuilder uriBuilder) {
		Product product = ProductFormDto.fromDto(productForm);
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
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
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		productRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}

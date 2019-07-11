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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gbessa.sovina.dtos.StoreDto;
import br.com.gbessa.sovina.dtos.StoreFormDto;
import br.com.gbessa.sovina.models.Store;
import br.com.gbessa.sovina.repositories.StoreRepository;

@RestController
@RequestMapping("/stores")
public class StoreController {

	@Autowired
	private StoreRepository storeRepository;
	
	@GetMapping("/all")
	public List<StoreDto> listAll() {
		List<Store> stores = storeRepository.findAll();
		return StoreDto.toDto(stores );
	}
	
	@GetMapping
	public Page<StoreDto> listPageble(Pageable pageble) {
		Page<Store> stores = storeRepository.findAll(pageble);
		return StoreDto.toDto(stores);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StoreDto> detail(@PathVariable Long id){
		Optional<Store> store = storeRepository.findById(id);
		return store.isPresent() ? ResponseEntity.ok(StoreDto.toDto(store.get())) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<StoreDto> insert(@RequestBody @Valid StoreFormDto storeForm, UriComponentsBuilder uriBuilder) {
		Store store = StoreFormDto.fromDto(storeForm);
		storeRepository.save(store);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(store.getId()).toUri();
		return ResponseEntity.created(uri).body(new StoreDto(store));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		storeRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}

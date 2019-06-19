package br.com.gbessa.sovina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gbessa.sovina.models.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {

	List<Price> findByProductName(String productName);
	
}

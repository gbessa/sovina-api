package br.com.gbessa.sovina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gbessa.sovina.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}

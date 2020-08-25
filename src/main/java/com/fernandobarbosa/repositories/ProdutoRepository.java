package com.fernandobarbosa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandobarbosa.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
}

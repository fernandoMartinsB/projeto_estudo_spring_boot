package com.fernandobarbosa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandobarbosa.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	
}

package com.fernandobarbosa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandobarbosa.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	Cliente findByEmail(String email);
	

}

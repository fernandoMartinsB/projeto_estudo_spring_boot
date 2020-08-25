package com.fernandobarbosa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandobarbosa.domain.Categoria;
import com.fernandobarbosa.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Optional<Categoria> buscar(Integer id) {
		return Optional.ofNullable(repo.findById(id).orElse(null));
	}

}

package com.fernandobarbosa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fernandobarbosa.domain.Categoria;
import com.fernandobarbosa.repositories.CategoriaRepository;
import com.fernandobarbosa.services.exceptions.DataIntegritException;
import com.fernandobarbosa.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Objeto não encontrado! Id: "+ id +", Tipo: " + Categoria.class.getName()
		));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			throw new DataIntegritException("Não é possível excliur uma categoria que possui produtos");
		}
	}

}

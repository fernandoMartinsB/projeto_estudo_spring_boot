package com.fernandobarbosa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fernandobarbosa.domain.Categoria;
import com.fernandobarbosa.domain.Produto;
import com.fernandobarbosa.repositories.CategoriaRepository;
import com.fernandobarbosa.repositories.ProdutoRepository;
import com.fernandobarbosa.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id +", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> findPage(String name, List<Integer> categoriaIds, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = repoCategoria.findAllById(categoriaIds);
		return repo.search(name, categorias, pageRequest);
	}

}

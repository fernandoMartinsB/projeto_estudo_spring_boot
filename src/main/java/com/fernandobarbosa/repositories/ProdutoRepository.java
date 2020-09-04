package com.fernandobarbosa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fernandobarbosa.domain.Categoria;
import com.fernandobarbosa.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:name% AND cat IN :categorias")	
	Page<Produto> search(@Param("name") String name, @Param("categorias")List<Categoria> categoriaIds, Pageable page);
	
	@Transactional(readOnly = true)
	Page<Produto>findDistinctByNomeContainingAndCategoriasIn (String name, List<Categoria> categoriaIds, Pageable page);
	
}

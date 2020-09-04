package com.fernandobarbosa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernandobarbosa.domain.Produto;
import com.fernandobarbosa.dto.ProdutoDTO;
import com.fernandobarbosa.resources.utils.Decoder;
import com.fernandobarbosa.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "ids", defaultValue = "") String ids,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value= "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) 
	{
		String decodedName = Decoder.decodeString(nome);
		List<Integer> decodedIds = Decoder.decodeList(ids);
		
		Page<Produto> list = service.findPage(decodedName, decodedIds, page,linesPerPage,orderBy,direction);
		Page<ProdutoDTO> listDTO = list.map(Produto -> new ProdutoDTO(Produto));
		return ResponseEntity.ok().body(listDTO);
	}	
}

package com.fernandobarbosa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernandobarbosa.domain.Cidade;
import com.fernandobarbosa.domain.Cliente;
import com.fernandobarbosa.domain.Endereco;
import com.fernandobarbosa.domain.enums.TipoCliente;
import com.fernandobarbosa.dto.ClienteDTO;
import com.fernandobarbosa.dto.ClienteNewDTO;
import com.fernandobarbosa.repositories.CidadeRepository;
import com.fernandobarbosa.repositories.ClienteRepository;
import com.fernandobarbosa.services.exceptions.DataIntegritException;
import com.fernandobarbosa.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository repoCidade;
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Objeto não encontrado! Id: "+ id +", Tipo: " + Cliente.class.getName()
		));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente cliente = find(obj.getId());
		return repo.save(updateData(cliente, obj));
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			throw new DataIntegritException("Não é possível excliur uma Cliente que possui pedidos");
		}
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}
	
	public Cliente fromNewDTO(ClienteNewDTO dto) {
		Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()));
		
		Optional<Cidade> cidade = repoCidade.findById(Integer.parseInt(dto.getCidadeId())); 
		
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cidade.orElse(null));
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(dto.getTelefone1());
		if(dto.getTelefone2() != null)
			cliente.getTelefones().add(dto.getTelefone2());
		if(dto.getTelefone3() != null)
			cliente.getTelefones().add(dto.getTelefone3());
		return cliente;
	}
	
	private Cliente updateData(Cliente cli, Cliente req) {
		cli.setNome(req.getNome());
		cli.setEmail(req.getEmail());
		return cli;
	}

}

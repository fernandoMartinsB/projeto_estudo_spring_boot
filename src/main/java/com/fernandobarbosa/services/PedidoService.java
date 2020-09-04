package com.fernandobarbosa.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernandobarbosa.domain.ItemPedido;
import com.fernandobarbosa.domain.PagamentoComBoleto;
import com.fernandobarbosa.domain.Pedido;
import com.fernandobarbosa.domain.enums.EstadoPagamento;
import com.fernandobarbosa.repositories.ItemPedidoRepository;
import com.fernandobarbosa.repositories.PagamentoRepository;
import com.fernandobarbosa.repositories.PedidoRepository;
import com.fernandobarbosa.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService serviceProduto;
	
	@Autowired
	private PagamentoRepository repoPagamento;
	
	@Autowired
	private ItemPedidoRepository repoItemPedido;
	
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: "+ id 
						+", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(@Valid Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		obj = repo.save(obj);
		repoPagamento.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(serviceProduto.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		repoItemPedido.saveAll(obj.getItens());
		return obj;
	}

}

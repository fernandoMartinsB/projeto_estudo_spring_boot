package com.fernandobarbosa;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fernandobarbosa.domain.Categoria;
import com.fernandobarbosa.domain.Cidade;
import com.fernandobarbosa.domain.Cliente;
import com.fernandobarbosa.domain.Endereco;
import com.fernandobarbosa.domain.Estado;
import com.fernandobarbosa.domain.ItemPedido;
import com.fernandobarbosa.domain.Pagamento;
import com.fernandobarbosa.domain.PagamentoComBoleto;
import com.fernandobarbosa.domain.PagamentoComCartao;
import com.fernandobarbosa.domain.Pedido;
import com.fernandobarbosa.domain.Produto;
import com.fernandobarbosa.domain.enums.EstadoPagamento;
import com.fernandobarbosa.domain.enums.TipoCliente;
import com.fernandobarbosa.repositories.CategoriaRepository;
import com.fernandobarbosa.repositories.CidadeRepository;
import com.fernandobarbosa.repositories.ClienteRepository;
import com.fernandobarbosa.repositories.EnderecoRepository;
import com.fernandobarbosa.repositories.EstadoRepository;
import com.fernandobarbosa.repositories.ItemPedidoRepository;
import com.fernandobarbosa.repositories.PagamentoRepository;
import com.fernandobarbosa.repositories.PedidoRepository;
import com.fernandobarbosa.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoEstudoSpringBootApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired 
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEstudoSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cama mesa e banho");
		Categoria cat4 = new Categoria(null,"Eletônicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decoração");
		Categoria cat7 = new Categoria(null,"Perfumaria");
		Categoria cat8 = new Categoria(null,"Vestuário");
		Categoria cat9 = new Categoria(null,"Bricolagem");
		Categoria cat10 = new Categoria(null,"Marcenaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de Escritório", 80.00);
		Produto p5 = new Produto(null, "Toalha", 80.00);
		Produto p6 = new Produto(null, "Coucha", 80.00);
		Produto p7 = new Produto(null, "TV True Color", 80.00);
		Produto p8 = new Produto(null, "Roçadeira", 80.00);
		Produto p9 = new Produto(null, "Abajour", 80.00);
		Produto p10 = new Produto(null, "Pendente", 80.00);
		Produto p11 = new Produto(null, "Shanpoo", 80.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat5.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat6.getProdutos().addAll(Arrays.asList(p8));
		cat7.getProdutos().addAll(Arrays.asList(p9,p10));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		this.categoriaRepository.saveAll(Arrays.asList(
				cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10
			));  
		this.produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		this.estadoRepository.saveAll(Arrays.asList(est1,est2));
		this.cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(
			null,
			"Maria Silvia",
			"maria@gmail.com",
			"36678912377",
			TipoCliente.PESSOAFISICA
		);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(
			null,
			"Rua Flores",
			"300",
			"Apto 203",
			"Jardin",
			"38220834",
			cli1,
			c1
		);
		Endereco e2 = new Endereco(
			null,
			"Avenida Matos",
			"105","Sala 800",
			"Centro",
			"38777012",
			cli1,
			c2
		);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		this.clienteRepository.save(cli1);
		this.enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(
				null, 
				sdf.parse("30/09/2017 10:32"),
				null, 
				cli1, 
				e1
			);
		Pedido ped2 =new Pedido(
				null, 
				sdf.parse("10/10/2017 19:35"),
				null, 
				cli1, 
				e2
			);
		
		Pagamento pagto1 = new PagamentoComCartao(
				null, 
				EstadoPagamento.QUITADO, 
				ped1, 
				6
			);
		
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Pagamento pagto2 = new PagamentoComBoleto(
				null, 
				EstadoPagamento.PENDENTE, 
				ped2, 
				null, 
				sdf.parse("20/10/2017")
			); 
		
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(
				ped1, 
				p1, 
				0.00, 
				1, 
				2000.00
			);
		ItemPedido ip2 = new ItemPedido(
				ped1, 
				p3, 
				0.00, 
				2, 
				80.00
			);
		ItemPedido ip3 = new ItemPedido(
				ped2, 
				p2, 
				100.00, 
				1, 
				800.00
			);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().add(ip3);
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
}

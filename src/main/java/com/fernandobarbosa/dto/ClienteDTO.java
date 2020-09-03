package com.fernandobarbosa.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fernandobarbosa.domain.Cliente;
import com.fernandobarbosa.services.valdation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message = "email é de preenchimento obrigatório")
	@Email(message = "o email deve ser válido")
	private String email;
	@NotEmpty(message = "o nome é de preenchimento obrigatório")
	@Length(min = 5, message = "O nome deve ter no minimo 5 caracteres")
	private String nome;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.email = cliente.getEmail();
		this.nome = cliente.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

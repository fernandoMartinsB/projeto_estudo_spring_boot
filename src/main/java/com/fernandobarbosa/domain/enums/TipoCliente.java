package com.fernandobarbosa.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	
	private int cod;
	private String descrição;
	
	private TipoCliente(int cod, String descrição) {
		this.cod = cod;
		this.descrição = descrição;
	}
	
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescrição() {
		return descrição;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}

	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}

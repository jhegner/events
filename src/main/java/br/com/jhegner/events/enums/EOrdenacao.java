package br.com.jhegner.events.enums;

import java.util.Arrays;

public enum EOrdenacao {

	SlotName("1", "codigoGrupoHotel"), 
	Group("2", "numeroPeticaoGrupo"), 
	Hotel("3", "nomeHotel"),
	FirstName("4", "primeiroNome"), 
	LastName("5", "ultimoNome");

	private String codigo;
	private String campo;

	private EOrdenacao(String codigo, String campo) {
		this.codigo = codigo;
		this.campo = campo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getCampo() {
		return campo;
	}

	public static EOrdenacao obterOrdenacaoPeloCodigo(String codigo) {
		return Arrays.asList(EOrdenacao.values()).stream().filter(o -> codigo.equalsIgnoreCase(o.getCodigo()))
				.findFirst().get();
	}

}

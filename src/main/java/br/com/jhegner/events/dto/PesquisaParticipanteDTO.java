package br.com.jhegner.events.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PesquisaParticipanteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numeroInscricao;
	private Long numeroInscricaoGrupo;
	private String primeiroNome;
	private String ultimoNome;
	private String pais;
	private String hotel;
	private String codigoGrupoLocacao;
	private Date dataInicio;
	private Date dataFim;
	private String orderPor1;
	private String orderPor2;
	private String orderPor3;
	private String qtdLinhas;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

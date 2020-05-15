package br.com.jhegner.events.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParticipanteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numeroInscricao;

	private Long numeroInscricaoGrupo;

	private String primeiroNome;

	private String ultimoNome;

	private String pais;

	private String idioma1;

	private String idioma2;

	private String idioma3;

	private String codigoHotel;

	private String nomeHotel;

	private String codigoGrupoLocacao;

	private String empresa;

	private String email;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

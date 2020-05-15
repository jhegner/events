package br.com.jhegner.events.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "TAB_PARTICIPANTE")
public class Participante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable = true, nullable = false, unique = true)
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

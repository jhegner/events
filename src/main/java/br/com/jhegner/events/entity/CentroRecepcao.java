package br.com.jhegner.events.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "TAB_CENTRO_RECEPCAO")
public class CentroRecepcao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(insertable = true, nullable = false, unique = true)
	private Long numeroIdentificador;
	private String endereco;
	private String complemento;
	private String zona;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;
	@OneToMany
	private List<GrupoRecepcao> gruposRecepcao;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

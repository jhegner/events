package br.com.jhegner.events.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "TAB_SIMULACAO_ALOCACAO")
public class Simulacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable = true, nullable = false, unique = true)
	private Long numeroSequencial;

	@OneToMany
	@JoinColumn(nullable = false)
	private Participante participante;

	@OneToMany
	@JoinColumn(nullable = false)
	private Evento evento;

	@OneToMany
	@JoinColumn(nullable = false)
	private GrupoRecepcao grupoRecepcao;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateTime;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

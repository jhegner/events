package br.com.jhegner.events.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.jhegner.events.enums.EAnotacao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "TAB_ANOTACAO")
public class Anotacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable = true, nullable = false, unique = true)
	private Long numeroSequencial;

	private String texto;

	@Enumerated(EnumType.STRING)
	private EAnotacao tipoAnotacao;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateTime;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

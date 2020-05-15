package br.com.jhegner.events.domain;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.jhegner.events.dto.PesquisaParticipanteDTO;
import br.com.jhegner.events.entity.Participante;

public class ParticipanteSpecification implements Specification<Participante> {

	private static final long serialVersionUID = 1L;

	private PesquisaParticipanteDTO filtro;

	public ParticipanteSpecification(PesquisaParticipanteDTO filtro) {
		super();
		this.filtro = filtro;
	}

	@Override
	public Predicate toPredicate(Root<Participante> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		List<Predicate> lp = new ArrayList<Predicate>();

		if (filtro.getNumeroInscricao() != null) {
			lp.add(cb.equal(root.get("numeroInscricao"), filtro.getNumeroInscricao()));
		}

		if (filtro.getNumeroInscricaoGrupo() != null) {
			lp.add(cb.equal(root.get("numeroInscricaoGrupo"), filtro.getNumeroInscricaoGrupo()));
		}

		if (isNotEmpty(filtro.getPrimeiroNome())) {
			lp.add(cb.like(cb.lower(root.get("primeiroNome")), "%" + filtro.getPrimeiroNome().toLowerCase() + "%"));
		}

		if (isNotEmpty(filtro.getPais())) {
			lp.add(cb.like(cb.lower(root.get("pais")), "%" + filtro.getPais().toLowerCase() + "%"));
		}

		if (isNotEmpty(filtro.getHotel())) {
			lp.add(cb.like(cb.lower(root.get("nomeHotel")), "%" + filtro.getHotel().toLowerCase() + "%"));
		}

		if (isNotEmpty(filtro.getUltimoNome())) {
			lp.add(cb.like(cb.lower(root.get("ultimoNome")), "%" + filtro.getUltimoNome().toLowerCase() + "%"));
		}

		if (isNotEmpty(filtro.getCodigoGrupoLocacao())) {
			lp.add(cb.equal(root.get("codigoGrupoLocacao"), filtro.getCodigoGrupoLocacao()));
		}

//		if (null != filtro.getDataInicio() && null != filtro.getDataFim()) {
//			lp.add(cb.between(root.get("dataEvento")));
//		}

//		List<String> dias = new ArrayList<String>();
//
//		if (filtro.isDia01()) {
//			dias.add("01-Ago");
//		}
//		if (filtro.isDia02()) {
//			dias.add("02-Ago");
//		}
//		if (filtro.isDia03()) {
//			dias.add("03-Ago");
//		}
//
//		if (!dias.isEmpty()) {
//			In<String> inClause = cb.in(cb.lower(root.get("dataCampo")));
//			for (String dia : dias) {
//				inClause.value(dia.toLowerCase());
//			}
//
//			lp.add(inClause);
//		}

		return cb.and(lp.toArray(new Predicate[0]));

	}
}

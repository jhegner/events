package br.com.jhegner.events.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.jhegner.events.domain.ParticipanteSpecification;
import br.com.jhegner.events.dto.HotelDTO;
import br.com.jhegner.events.dto.ParticipanteDTO;
import br.com.jhegner.events.dto.PesquisaParticipanteDTO;
import br.com.jhegner.events.entity.Participante;
import br.com.jhegner.events.enums.Ordenacao;
import br.com.jhegner.events.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {

	@Autowired
	private ParticipanteRepository repository;

	@Transactional
	public void incluir(ParticipanteDTO dto) {

		Participante c = preencheObjParticipante(dto);

		repository.save(c);
	}

	public List<ParticipanteDTO> pesquisarPorNumeroInscricao(PesquisaParticipanteDTO dtoPesq) {

		List<ParticipanteDTO> dtos = new ArrayList<ParticipanteDTO>();
		Participante obj = repository.findById(dtoPesq.getNumeroInscricao()).orElse(null);
		dtos.add(preencheDtoParticipante(obj));

		return dtos;
	}

	public List<ParticipanteDTO> search(PesquisaParticipanteDTO dtoPesq) {

		Specification<Participante> spec = preparaSpecification(dtoPesq);
		Pageable pageable = preparaPageable(dtoPesq);
		Page<Participante> ret = repository.findAll(spec, pageable);

		List<ParticipanteDTO> dtos = new ArrayList<ParticipanteDTO>();

		for (Participante obj : ret.getContent()) {
			dtos.add(preencheDtoParticipante(obj));
		}

		return dtos;
	}

	private Pageable preparaPageable(PesquisaParticipanteDTO dtoPesq) {

		List<String> order = new ArrayList<String>();

		if (StringUtils.isNotEmpty(dtoPesq.getOrderPor1())) {
			order.add(Ordenacao.obterOrdenacaoPeloCodigo(dtoPesq.getOrderPor1()).getCampo());
		}
		if (StringUtils.isNotEmpty(dtoPesq.getOrderPor2())) {
			order.add(Ordenacao.obterOrdenacaoPeloCodigo(dtoPesq.getOrderPor2()).getCampo());
		}
		if (StringUtils.isNotEmpty(dtoPesq.getOrderPor3())) {
			order.add(Ordenacao.obterOrdenacaoPeloCodigo(dtoPesq.getOrderPor3()).getCampo());
		}

		Sort sort = null;

		if (!order.isEmpty()) {
			sort = Sort.by(Direction.ASC, order.toArray(new String[0]));
		} else {
			sort = Sort.by(Direction.ASC, Ordenacao.FirstName.getCampo());
		}

		Pageable pageable = PageRequest.of(0, Integer.parseInt(dtoPesq.getQtdLinhas()), sort);
		return pageable;
	}

	private Specification<Participante> preparaSpecification(PesquisaParticipanteDTO dtoPesq) {
		return new ParticipanteSpecification(dtoPesq);
	}

	@Cacheable("idiomas")
	public List<String> pesquisaTodosIdiomas() {

		List<String[]> objs = repository.findByAsStringAndSortIdiomas();

		Set<String> setIdiomas = new HashSet<>();

		for (String[] objArr : objs) {
			for (String idioma : objArr) {
				setIdiomas.add(idioma);
			}
		}

		return new ArrayList<>(setIdiomas);
	}

	@Cacheable("gruposLocacao")
	public List<String> pesquisaTodosGruposLocacao() {
		return repository.findByAsStringAndSortGrupoLocacao(Sort.by("codigoGrupoLocacao"));
	}

	@Cacheable("hoteis")
	public List<HotelDTO> pesquisaTodosHoteis() {

		List<String[]> objs = repository.findAllHoteis(Sort.by("nomeHotel"));

		List<HotelDTO> hoteis = new ArrayList<HotelDTO>(objs.size());

		for (String[] obj : objs) {
			hoteis.add(new HotelDTO(obj[0], obj[1]));
		}

		return hoteis;
	}

	private Participante preencheObjParticipante(ParticipanteDTO dto) {

		Participante entity = new Participante();

		entity.setNumeroInscricao(dto.getNumeroInscricao());
		entity.setNumeroInscricaoGrupo(dto.getNumeroInscricaoGrupo());
		entity.setPrimeiroNome(dto.getPrimeiroNome());
		entity.setUltimoNome(dto.getUltimoNome());
		entity.setPais(dto.getPais());
		entity.setIdioma1(dto.getIdioma1());
		entity.setIdioma1(dto.getIdioma2());
		entity.setIdioma3(dto.getIdioma3());
		entity.setCodigoHotel(dto.getCodigoHotel());
		entity.setNomeHotel(dto.getNomeHotel());
		entity.setCodigoGrupoLocacao(dto.getCodigoGrupoLocacao());

		return entity;
	}

	private ParticipanteDTO preencheDtoParticipante(Participante entity) {

		ParticipanteDTO dto = new ParticipanteDTO();

		dto.setNumeroInscricao(entity.getNumeroInscricao());
		dto.setNumeroInscricaoGrupo(entity.getNumeroInscricaoGrupo());
		dto.setPrimeiroNome(entity.getPrimeiroNome());
		dto.setUltimoNome(entity.getUltimoNome());
		dto.setPais(entity.getPais());
		dto.setIdioma1(entity.getIdioma1());
		dto.setIdioma1(entity.getIdioma2());
		dto.setIdioma3(entity.getIdioma3());
		dto.setCodigoHotel(entity.getCodigoHotel());
		dto.setNomeHotel(entity.getNomeHotel());
		dto.setCodigoGrupoLocacao(entity.getCodigoGrupoLocacao());

		return dto;
	}
}

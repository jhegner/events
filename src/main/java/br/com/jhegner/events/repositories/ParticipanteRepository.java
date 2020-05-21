package br.com.jhegner.events.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.jhegner.events.entity.Participante;

public interface ParticipanteRepository
		extends JpaRepository<Participante, Long>, JpaSpecificationExecutor<Participante> {

	@Query("select distinct(p.codigoGrupoLocacao) from Participante p")
	List<String> findByAsStringAndSortGrupoLocacao(Sort sort);

	@Query("select distinct(p.nomeHotel) from Participante p")
	List<String> findAllHoteis(Sort sort);

	@Query("select p.idioma1, p.idioma2, p.idioma3 from Participante p group by p.idioma1, p.idioma2, p.idioma3")
	List<String[]> findByAsStringAndSortIdiomas();
}

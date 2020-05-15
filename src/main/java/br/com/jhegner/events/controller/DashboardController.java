package br.com.jhegner.events.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.jhegner.events.dto.HotelDTO;
import br.com.jhegner.events.dto.PesquisaParticipanteDTO;
import br.com.jhegner.events.service.ParticipanteService;

@Controller
public class DashboardController {

	@Autowired
	private ParticipanteService cs;

	@GetMapping("/participante")
	public String goParticipante(Model model) {

		// slots
		List<String> slots = cs.pesquisaTodosGruposLocacao();
		model.addAttribute("slots", slots);

		// hoteis
		List<HotelDTO> hoteis = cs.pesquisaTodosHoteis();
		model.addAttribute("hoteis", hoteis);

		// participantes
		model.addAttribute("participante", null);
		model.addAttribute("filtroDto", new PesquisaParticipanteDTO());

		return "participante";
	}

	@GetMapping("/configuracao")
	public String goConfiguracao() {
		return "configuracao";
	}

	@GetMapping("/inicio")
	public String goVoltar() {
		return "/index";
	}

}

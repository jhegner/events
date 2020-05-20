package br.com.jhegner.events.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jhegner.events.dto.HotelDTO;
import br.com.jhegner.events.dto.ParticipanteDTO;
import br.com.jhegner.events.dto.PesquisaParticipanteDTO;
import br.com.jhegner.events.exceptions.ValidaFiltroPesquisaException;
import br.com.jhegner.events.service.ParticipanteService;

@Controller
@RequestMapping("/participante")
public class ParticipanteController {

	@Autowired
	private ParticipanteService cs;

	@GetMapping
	public String goDefault(Model model) {

		// hoteis
		List<HotelDTO> hoteis = cs.pesquisaTodosHoteis();
		model.addAttribute("hoteis", hoteis);

		// participantes
		model.addAttribute("participante", null);
		model.addAttribute("filtroDto", new PesquisaParticipanteDTO());

		return "participante";
	}

	@PostMapping("/pesquisa")
	public String pesquisar(@Validated @ModelAttribute("filtroDto") PesquisaParticipanteDTO pesqDto,
			BindingResult bindingResult, Model model) {

		List<ParticipanteDTO> list = pesquisar(pesqDto);
		List<HotelDTO> hoteis = cs.pesquisaTodosHoteis();

		model.addAttribute("hoteis", hoteis);
		model.addAttribute("participantes", list);
		model.addAttribute("pesqDto", pesqDto);

		if (null == list || list.isEmpty()) {
			model.addAttribute("message_info", "Pesquisa não encontrou resultados");
		}

		return "participante";
	}

	@GetMapping("/limpar")
	public String limpar(Model model) {
		return "redirect:/participante";
	}

	@GetMapping("/novo")
	public String novo(Model model) {

		// hoteis
		List<HotelDTO> hoteis = cs.pesquisaTodosHoteis();
		model.addAttribute("hoteis", hoteis);

		// participantes
		model.addAttribute("participanteDto", new ParticipanteDTO());

		return "participante-manutencao";
	}

	@PostMapping("/altera")
	public String salvar(@Validated @ModelAttribute("participanteDto") ParticipanteDTO dto, BindingResult bindingResult,
			Model model) {

		List<HotelDTO> hoteis = cs.pesquisaTodosHoteis();

		model.addAttribute("hoteis", hoteis);
		model.addAttribute("participanteDto", new ParticipanteDTO());

		model.addAttribute("message_success", "Manutencao realizada com sucesso");

		return "participante-manutencao";
	}

	private List<ParticipanteDTO> pesquisar(PesquisaParticipanteDTO pesqDto) {

		List<ParticipanteDTO> ret = null;

		if (null != pesqDto) {

			if (null != pesqDto.getNumeroInscricao()) {
				ret = cs.pesquisarPorNumeroInscricao(pesqDto);
			} else {
				ret = cs.search(pesqDto);
			}
		}

		return ret;
	}

	@ExceptionHandler(ValidaFiltroPesquisaException.class)
	public String handleStorageFileNotFound(ValidaFiltroPesquisaException exc, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message_warning", exc.getMessage());
		return "redirect:/participante";
	}

}

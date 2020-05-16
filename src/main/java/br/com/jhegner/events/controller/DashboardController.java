package br.com.jhegner.events.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@GetMapping
	public String goDefault() {
		return "dashboard";
	}

	@GetMapping("/participante")
	public String goParticipante() {
		return "redirect:/participante";
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

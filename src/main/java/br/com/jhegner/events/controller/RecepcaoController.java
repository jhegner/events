package br.com.jhegner.events.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recepcao")
public class RecepcaoController {

	@GetMapping("/centro")
	public String goCentro(Model model) {

		return "redirect:/centro";
	}

	@GetMapping("/grupo")
	public String goGrupo(Model model) {

		return "redirect:/grupo";
	}
}

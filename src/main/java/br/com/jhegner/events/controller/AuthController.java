package br.com.jhegner.events.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/entrar")
	public String goDashboard(Model model) {

		return "redirect:/dashboard";
	}

	@GetMapping("/sair")
	public String goOut(Model model) {

		return "redirect:/";
	}

}

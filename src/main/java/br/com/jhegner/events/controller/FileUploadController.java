package br.com.jhegner.events.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jhegner.events.enums.ETipoArquivo;
import br.com.jhegner.events.exceptions.ProcessaArquivoException;
import br.com.jhegner.events.service.FileUploadService;

@Controller
public class FileUploadController {

	private FileUploadService fileService;

	@Autowired
	public FileUploadController(FileUploadService fileService) {
		this.fileService = fileService;
	}

	@PostMapping("/upload")
	public String handleFileUploadParticipantes(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		if (null != file && StringUtils.isNotEmpty(file.getOriginalFilename())) {
			fileService.processarFile(file, ETipoArquivo.PARTICIPANTE);
			redirectAttributes.addFlashAttribute("message_success", "Arquivo carregado com sucesso!");
		} else {
			redirectAttributes.addFlashAttribute("message_error", "Erro - arquivo não informado.");
		}

		return "redirect:/configuracao";
	}

	@ExceptionHandler(ProcessaArquivoException.class)
	public String handleStorageFileNotFound(ProcessaArquivoException exc, RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("message_error", exc.getMessage());

		return "redirect:/configuracao";
	}

}

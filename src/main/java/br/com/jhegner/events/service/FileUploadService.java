package br.com.jhegner.events.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.jhegner.events.dto.ParticipanteDTO;
import br.com.jhegner.events.enums.ETipoArquivo;
import br.com.jhegner.events.exceptions.ProcessaArquivoException;

@Service
public class FileUploadService {

	@Autowired
	private ParticipanteService participanteService;

	public void processarFile(MultipartFile mf, ETipoArquivo tipo) throws ProcessaArquivoException {

		try {
			processa(mf, tipo);
		} catch (IOException | NumberFormatException e) {
			throw new ProcessaArquivoException("Erro ao processar arquivo", e);
		}

	}

	private void processa(MultipartFile mf, ETipoArquivo tipo) throws IOException {

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(mf.getInputStream()))) {
			while (reader.ready()) {
				String line = reader.readLine();
				if (ETipoArquivo.PARTICIPANTE.equals(tipo))
					lerLinhaFileParticipante(line);
			}
		}
	}

	private void lerLinhaFileParticipante(String line) {

		String[] campos = line.split(",");

		criaObjetoParticipante(campos);
	}

	private void criaObjetoParticipante(String[] campos) {

		ParticipanteDTO dto = new ParticipanteDTO();

		dto.setNumeroInscricao(Long.parseLong(campos[0]));
		dto.setNumeroInscricaoGrupo(Long.parseLong(campos[1]));
		dto.setPrimeiroNome(campos[2]);
		dto.setUltimoNome(campos[3]);
		dto.setPais(campos[4]);
		dto.setIdioma1(campos[5]);
		dto.setIdioma2(campos[6]);
		dto.setIdioma3(campos[7]);
		dto.setCodigoHotel(campos[8]);
		dto.setNomeHotel(campos[9]);
		dto.setCodigoGrupoLocacao(campos[10]);
		dto.setEmpresa(campos[11]);
		dto.setEmail(campos[12]);

		registraObjNaBase(dto);
	}

	private void registraObjNaBase(ParticipanteDTO dto) {
		participanteService.incluir(dto);
	}

}

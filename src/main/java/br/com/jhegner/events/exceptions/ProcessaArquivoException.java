package br.com.jhegner.events.exceptions;

public class ProcessaArquivoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProcessaArquivoException() {
		super();
	}

	public ProcessaArquivoException(String message) {
		super(message);
	}

	public ProcessaArquivoException(String message, Throwable cause) {
		super(message, cause);
	}
}

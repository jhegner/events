package br.com.jhegner.events.exceptions;

public class ValidaFiltroPesquisaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidaFiltroPesquisaException() {
		super();
	}

	public ValidaFiltroPesquisaException(String message) {
		super(message);
	}

	public ValidaFiltroPesquisaException(String message, Throwable cause) {
		super(message, cause);
	}
}

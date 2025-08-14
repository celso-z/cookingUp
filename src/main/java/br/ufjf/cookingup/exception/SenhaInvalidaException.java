package br.ufjf.cookingup.exception;

public class SenhaInvalidaException extends RuntimeException {
	 public SenhaInvalidaException() {
	        super("Senha inválida");
	 }
}

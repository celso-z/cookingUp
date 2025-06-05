package br.ufjf.cookingup.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class ReceitaLivroReceita {
	@Id
	private Long id;
	@ManyToOne
	private LivroReceita livro;
	@ManyToOne
	private Receita receita;
}

package br.ufjf.cookingup.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class AlternativaIngrediente {
	@Id 
	private Long id;
	@OneToOne
	Ingrediente ingrediente;
	@ManyToOne
	Ingrediente alternativa;
}

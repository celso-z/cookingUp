package br.ufjf.cookingup.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlternativaIngrediente {
	@Id 
	private Long id;
	@OneToOne
	Ingrediente ingrediente;
	@ManyToOne
	Ingrediente alternativa;
}

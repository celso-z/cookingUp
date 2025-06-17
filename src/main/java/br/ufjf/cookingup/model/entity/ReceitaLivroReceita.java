package br.ufjf.cookingup.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaLivroReceita {
	@Id
	private Long id;

	@ManyToOne
	private LivroReceita livro;

	@ManyToOne
	private Receita receita;
}

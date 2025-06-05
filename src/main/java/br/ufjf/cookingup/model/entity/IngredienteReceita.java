package br.ufjf.cookingup.model.entity;

import java.util.ArrayList;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteReceita {
	@Id
	private Long id;
	private Integer quantidade;
	private String observacoes;
	private String unidade;
	@ManyToOne
	private Ingrediente ingrediente;
}

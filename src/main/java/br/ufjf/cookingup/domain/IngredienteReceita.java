package br.ufjf.cookingup.domain;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteReceita {
	private Integer quantidade;
	private String observacoes;
	private String unidade;
	private ArrayList<Ingrediente> alternativas;
}

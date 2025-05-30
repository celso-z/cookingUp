package br.ufjf.cookingup.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {
	private Long id;
	private String nome;
	private String unidadePadrao;
}

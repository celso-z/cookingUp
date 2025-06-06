package br.ufjf.cookingup.model.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {
	@Id
	private Long id;
	private String nome;
	private String unidadePadrao;
}

package br.ufjf.cookingup.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {
	@Id
	private Long id;
	private String nome;
	private String unidadePadrao;
}

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
public class LivroReceita {
	@Id
	private Long id;
	private String titulo;
	@ManyToOne
	private Membro proprietario;
}

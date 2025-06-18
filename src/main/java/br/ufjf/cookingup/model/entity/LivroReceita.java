package br.ufjf.cookingup.model.entity;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LivroReceita {
	@Id
	private Long id;
	private String titulo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "membro_id", nullable = false)
	private Membro proprietario;
}

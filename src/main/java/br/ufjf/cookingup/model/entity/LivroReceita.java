package br.ufjf.cookingup.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LivroReceita {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_livro_receita")
	@SequenceGenerator(name = "seq_livro_receita", sequenceName = "seq_livro_receita", allocationSize = 1)
	private Long id;

	private String titulo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "membro_id", nullable = false)
	private Membro proprietario;
	private LocalDate dataInicio;
	private LocalDate dataFim;
}

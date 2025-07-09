package br.ufjf.cookingup.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaLivroReceita {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_receita_livro_receita")
	@SequenceGenerator(name = "seq_receita_livro_receita", sequenceName = "seq_receita_livro_receita", allocationSize = 1)
	private Long id;

	@ManyToOne
	private LivroReceita livro;

	@ManyToOne
	private Receita receita;

	private LocalDate dataInicio;
	private LocalDate dataFim;
}

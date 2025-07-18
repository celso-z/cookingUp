package br.ufjf.cookingup.model.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_avaliacao")
	@SequenceGenerator(name = "seq_avaliacao", sequenceName = "seq_avaliacao", allocationSize = 1)
	private Long id;

	private Integer nota;
	private String consideracoes;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	@ManyToOne
	private Membro avaliador;

	@ManyToOne
	private Receita receitaAvaliada;


}

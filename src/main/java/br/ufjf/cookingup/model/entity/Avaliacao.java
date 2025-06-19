package br.ufjf.cookingup.model.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
	@Id
	private Long id;
	private Integer nota;
	private String consideracoes;
	private LocalDate data;
	@ManyToOne
	private Membro avaliador;

	@ManyToOne
	private Receita receitaAvaliada;

}

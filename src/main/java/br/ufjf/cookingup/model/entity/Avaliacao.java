package br.ufjf.cookingup.model.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	private Date data;
	private Membro avaliador;
	private Receita receitaAvaliada;

}

package br.ufjf.cookingup.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
	private Long id;
	private Integer nota;
	private String consideracoes;
	private Date data;
	private Membro avaliador;
	private Receita receitaAvaliada;

}

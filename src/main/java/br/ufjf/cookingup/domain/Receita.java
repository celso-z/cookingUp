package br.ufjf.cookingup.domain;

import java.sql.Date;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receita {
	private String titulo;
	private String descricao;
	private Integer porcoes;
	private String modoPreparo;
	private Integer tempoPreparo;
	private String imagemUrl;
	private Date dataCadastro;
	private Date dataFim;
	private ArrayList<IngredienteReceita> ingredientesReceita;
	private Categoria categoria;
}

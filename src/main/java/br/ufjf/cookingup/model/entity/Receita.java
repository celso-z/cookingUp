package br.ufjf.cookingup.model.entity;

import java.sql.Date;
import java.util.ArrayList;

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
public class Receita {
	@Id
	private Long id;
	private String titulo;
	private String descricao;
	private Integer porcoes;
	private String modoPreparo;
	private Integer tempoPreparo;
	private String imagemUrl;
	private Date dataCadastro;
	private Date dataFim;
	//private ArrayList<IngredienteReceita> ingredientesReceita;

	@ManyToOne
	private Categoria categoria;
}

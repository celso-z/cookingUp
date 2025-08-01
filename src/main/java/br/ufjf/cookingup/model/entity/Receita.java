package br.ufjf.cookingup.model.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Receita {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_receita")
	@SequenceGenerator(name = "seq_receita", sequenceName = "seq_receita", allocationSize = 1)
	private Long id;

	private String titulo;
	private String descricao;
	private Integer porcoes;
	private String modoPreparo;
	private Integer tempoPreparo;
	private String imagemUrl;
	private LocalDate dataCadastro;
	private LocalDate dataFim;

	//Criar ingrediente receita (receita,ingrediente,quantidade)
	//@OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	//private List<IngredienteReceita> ingredientesReceita = new ArrayList<>();

	@ManyToOne
	private Categoria categoria;
}

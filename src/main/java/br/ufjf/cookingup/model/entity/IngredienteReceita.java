package br.ufjf.cookingup.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteReceita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantidade;
	private String observacoes;
	private String unidade;
	@ManyToOne
	private Ingrediente ingrediente;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	@ManyToOne
	private Receita receita;
}

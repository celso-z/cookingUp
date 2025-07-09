package br.ufjf.cookingup.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ingrediente")
	@SequenceGenerator(name = "seq_ingrediente", sequenceName = "seq_ingrediente", allocationSize = 1)
	private Long id;

	private String nome;
	private String unidadePadrao;
	private LocalDate dataInicio;
	private LocalDate dataFim;
}

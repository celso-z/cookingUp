package br.ufjf.cookingup.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AlternativaIngrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alternativa_ingrediente")
	@SequenceGenerator(name = "seq_alternativa_ingrediente", sequenceName = "seq_alternativa_ingrediente", allocationSize = 1)
	private Long id;

	@OneToOne
	Ingrediente ingrediente;
	@ManyToOne
	Ingrediente alternativa;
	private LocalDate dataInicio;
	private LocalDate dataFim;
}

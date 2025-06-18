package br.ufjf.cookingup.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;

@Data
@NoArgsConstructor // Mantenha, se precisar
@AllArgsConstructor // Mantenha, se precisar
@MappedSuperclass
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	// Altere o tipo de Date para LocalDate
	private LocalDate dataCadastro; // <-- Corrigido
	private LocalDate dataFim;      // <-- Corrigido
	private String fotoPerfil;
}
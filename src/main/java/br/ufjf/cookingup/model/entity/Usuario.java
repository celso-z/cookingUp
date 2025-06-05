package br.ufjf.cookingup.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;

@Data
@Entity
@Inheritance
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {
	@Id
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Date dataCadastro;
	private Date dataFim;
	private String fotoPerfil;
}

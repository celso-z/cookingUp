package br.ufjf.cookingup.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Date dataCadastro;
	private Date dataFim;
	private String fotoPerfil;
}

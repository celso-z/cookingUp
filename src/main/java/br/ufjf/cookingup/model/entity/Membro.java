package br.ufjf.cookingup.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// @AttributeOverrides para mapear TODOS os campos herdados
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "id")),
		@AttributeOverride(name = "nome", column = @Column(name = "nome")),
		@AttributeOverride(name = "email", column = @Column(name = "email")),
		@AttributeOverride(name = "senha", column = @Column(name = "senha")),
		@AttributeOverride(name = "dataCadastro", column = @Column(name = "data_cadastro")), // Use snake_case para nomes de coluna SQL
		@AttributeOverride(name = "dataFim", column = @Column(name = "data_fim")),           // Use snake_case
		@AttributeOverride(name = "fotoPerfil", column = @Column(name = "foto_perfil"))       // Use snake_case
})
public class Membro extends Usuario {
	private Integer nivel;
// Se for usar listas, use List e anote corretamente:
// @OneToMany
// private List<LivroReceita> livrosReceitas;
// @OneToMany
// private List<Receita> receitasFavoritas;
}
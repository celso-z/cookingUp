package br.ufjf.cookingup.model.entity;

import java.util.ArrayList;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membro extends Usuario {
	@Id
	private Long id;
	private Integer nivel;
	
	//private ArrayList<LivroReceita> livrosReceitas;
	//private ArrayList<Receita> receitasFavoritas;
}

package br.ufjf.cookingup.domain;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membro extends Usuario {
	private Integer nivel;
	
	private ArrayList<LivroReceita> livrosReceitas;
	private ArrayList<Receita> receitasFavoritas;
}

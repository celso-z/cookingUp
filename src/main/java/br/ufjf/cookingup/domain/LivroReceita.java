package br.ufjf.cookingup.domain;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroReceita {
	private String titulo;
	private ArrayList<Receita> receitas;
}

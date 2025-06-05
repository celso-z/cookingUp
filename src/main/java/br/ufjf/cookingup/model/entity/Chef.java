package br.ufjf.cookingup.model.entity;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chef extends Usuario {
	private String biografia;
	private ArrayList<Receita> receitas;
}

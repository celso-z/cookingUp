package br.ufjf.cookingup.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembroDTO {
    private Long id;
    private Integer nivel;
    // private ArrayList<LivroReceita> livrosReceitas;
    // private ArrayList<Receita> receitasFavoritas;
}

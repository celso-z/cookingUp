package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Receita;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ChefDTO {
    private String biografia;
    private ArrayList<Receita> receitas;
}

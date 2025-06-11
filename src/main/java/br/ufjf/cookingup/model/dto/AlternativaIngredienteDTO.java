package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Ingrediente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlternativaIngredienteDTO {
    private Long id;
    private Ingrediente ingrediente;
    private Ingrediente alternativa;
}

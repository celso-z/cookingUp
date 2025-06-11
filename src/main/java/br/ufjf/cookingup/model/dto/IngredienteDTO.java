package br.ufjf.cookingup.model.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredienteDTO {
    private Long id;
    private String nome;
    private String unidadePadrao;
}

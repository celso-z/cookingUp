package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Ingrediente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredienteDTO {
    private Long id;
    private String nome;
    private String unidadePadrao;

    public IngredienteDTO(Ingrediente ingrediente) {
        if (ingrediente == null)
            return;
        this.id = ingrediente.getId();
        this.nome = ingrediente.getNome();
        this.unidadePadrao = ingrediente.getUnidadePadrao();
    }

}

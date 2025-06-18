package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaDTO {
    private Long id;
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        if (categoria == null)
            return;
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}

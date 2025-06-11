package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Membro;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroReceitaDTO {
    private Long id;
    private String titulo;
    private Membro proprietario;
}

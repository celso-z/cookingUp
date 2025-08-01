package br.ufjf.cookingup.model.validator;

import br.ufjf.cookingup.model.dto.CategoriaDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValidator {

    public void validar(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getNome() == null || categoriaDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazio");
        }

        if (categoriaDTO.getNome().length() > 50) {
            throw new IllegalArgumentException("Nome da categoria não pode ter mais de 50 caracteres");
        }
    }
}
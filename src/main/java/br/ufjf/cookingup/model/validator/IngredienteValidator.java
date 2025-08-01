package br.ufjf.cookingup.model.validator;

import br.ufjf.cookingup.model.dto.IngredienteDTO;
import org.springframework.stereotype.Component;

@Component
public class IngredienteValidator {

    public void validar(IngredienteDTO ingredienteDTO) {
        if (ingredienteDTO.getNome() == null || ingredienteDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do ingrediente não pode ser vazio");
        }

        if (ingredienteDTO.getUnidadePadrao() == null || ingredienteDTO.getUnidadePadrao().trim().isEmpty()) {
            throw new IllegalArgumentException("Unidade padrão não pode ser vazia");
        }

        if (ingredienteDTO.getNome().length() > 100) {
            throw new IllegalArgumentException("Nome do ingrediente não pode ter mais de 100 caracteres");
        }
    }
}
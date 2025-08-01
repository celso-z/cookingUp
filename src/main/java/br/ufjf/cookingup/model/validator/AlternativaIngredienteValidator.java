package br.ufjf.cookingup.model.validator;

import br.ufjf.cookingup.model.dto.AlternativaIngredienteDTO;
import org.springframework.stereotype.Component;

@Component
public class AlternativaIngredienteValidator {

    public void validar(AlternativaIngredienteDTO alternativaIngredienteDTO) {
        if (alternativaIngredienteDTO.getIdIngrediente() == null ||
                alternativaIngredienteDTO.getIdIngrediente() <= 0) {
            throw new IllegalArgumentException("ID do ingrediente principal deve ser positivo");
        }

        if (alternativaIngredienteDTO.getIdAlternativa() == null ||
                alternativaIngredienteDTO.getIdAlternativa() <= 0) {
            throw new IllegalArgumentException("ID do ingrediente alternativo deve ser positivo");
        }

        if (alternativaIngredienteDTO.getIdIngrediente().equals(
                alternativaIngredienteDTO.getIdIngrediente())) {
            throw new IllegalArgumentException("Ingrediente principal não pode ser igual ao alternativo");
        }
    }
}
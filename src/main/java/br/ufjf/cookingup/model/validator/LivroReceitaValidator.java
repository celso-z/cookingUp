package br.ufjf.cookingup.model.validator;

import br.ufjf.cookingup.model.dto.LivroReceitaDTO;
import org.springframework.stereotype.Component;

@Component
public class LivroReceitaValidator {

    public void validar(LivroReceitaDTO livroReceitaDTO) {
        if (livroReceitaDTO.getTitulo() == null || livroReceitaDTO.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título do livro de receita não pode ser vazio");
        }

        if (livroReceitaDTO.getIdProprietario() == null || livroReceitaDTO.getIdProprietario() <= 0) {
            throw new IllegalArgumentException("ID do proprietário deve ser positivo");
        }

        if (livroReceitaDTO.getTitulo().length() > 200) {
            throw new IllegalArgumentException("Título não pode ter mais de 200 caracteres");
        }

        if (livroReceitaDTO.getNomeProprietario() == null || livroReceitaDTO.getNomeProprietario().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do proprietário não pode ser vazio");
        }
    }
}
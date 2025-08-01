package br.ufjf.cookingup.model.validator;

import br.ufjf.cookingup.model.dto.AvaliacaoDTO;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoValidator {

    public void validar(AvaliacaoDTO avaliacaoDTO) {
        if (avaliacaoDTO.getNota() == null || avaliacaoDTO.getNota() < 1 || avaliacaoDTO.getNota() > 5) {
            throw new IllegalArgumentException("Nota deve estar entre 1 e 5");
        }

        if (avaliacaoDTO.getIdAvaliador() == null) {
            throw new IllegalArgumentException("ID do avaliador é obrigatório");
        }
        if (avaliacaoDTO.getIdAvaliador() != null && avaliacaoDTO.getIdAvaliador() <= 0) {
            throw new IllegalArgumentException("ID do avaliador deve ser positivo");
        }

        if (avaliacaoDTO.getIdReceitaAvaliada() == null) {
            throw new IllegalArgumentException("ID da receita é obrigatório");
        }
        if (avaliacaoDTO.getIdReceitaAvaliada() != null && avaliacaoDTO.getIdReceitaAvaliada() <= 0) {
            throw new IllegalArgumentException("ID da receita deve ser positivo");
        }

        if (avaliacaoDTO.getConsideracoes() != null && avaliacaoDTO.getConsideracoes().length() > 500) {
            throw new IllegalArgumentException("Considerações não podem ter mais de 500 caracteres");
        }
    }
}
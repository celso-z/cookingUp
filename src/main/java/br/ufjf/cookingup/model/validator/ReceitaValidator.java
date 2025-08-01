package br.ufjf.cookingup.model.validator;

import br.ufjf.cookingup.model.dto.ReceitaDTO;
import org.springframework.stereotype.Component;

@Component
public class ReceitaValidator {

    public void validar(ReceitaDTO receitaDTO) {
        if (receitaDTO.getTitulo() == null || receitaDTO.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título da receita não pode ser vazio");
        }

        if (receitaDTO.getTitulo().length() > 200) {
            throw new IllegalArgumentException("Título não pode ter mais de 200 caracteres");
        }

        if (receitaDTO.getDescricao() == null || receitaDTO.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição da receita não pode ser vazia");
        }

        if (receitaDTO.getDescricao().length() > 1000) {
            throw new IllegalArgumentException("Descrição não pode ter mais de 1000 caracteres");
        }

        if (receitaDTO.getPorcoes() == null || receitaDTO.getPorcoes() <= 0) {
            throw new IllegalArgumentException("Número de porções deve ser positivo");
        }

        if (receitaDTO.getPorcoes() > 100) {
            throw new IllegalArgumentException("Número de porções não pode ser maior que 100");
        }

        if (receitaDTO.getModoPreparo() == null || receitaDTO.getModoPreparo().trim().isEmpty()) {
            throw new IllegalArgumentException("Modo de preparo não pode ser vazio");
        }

        if (receitaDTO.getModoPreparo().length() > 5000) {
            throw new IllegalArgumentException("Modo de preparo não pode ter mais de 5000 caracteres");
        }

        if (receitaDTO.getTempoPreparo() == null || receitaDTO.getTempoPreparo() <= 0) {
            throw new IllegalArgumentException("Tempo de preparo deve ser positivo (em minutos)");
        }

        if (receitaDTO.getTempoPreparo() > 1440) { // 24 horas em minutos
            throw new IllegalArgumentException("Tempo de preparo não pode ser maior que 24 horas");
        }

        if (receitaDTO.getIdCategoria() == null || receitaDTO.getIdCategoria() <= 0) {
            throw new IllegalArgumentException("ID da categoria deve ser positivo");
        }

        if (receitaDTO.getImagemUrl() != null && receitaDTO.getImagemUrl().length() > 500) {
            throw new IllegalArgumentException("URL da imagem não pode ter mais de 500 caracteres");
        }
    }
}
package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.ReceitaDTO;
import br.ufjf.cookingup.model.entity.Receita;
import br.ufjf.cookingup.model.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public ReceitaDTO findById(Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Receita não encontrada com id: " + id));
        return ReceitaDTO.create(receita);
    }
}

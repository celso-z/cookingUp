package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.AvaliacaoDTO;
import br.ufjf.cookingup.model.entity.Avaliacao;
import br.ufjf.cookingup.model.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoDTO findById(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada com id: " + id));
        return AvaliacaoDTO.create(avaliacao);
    }
}

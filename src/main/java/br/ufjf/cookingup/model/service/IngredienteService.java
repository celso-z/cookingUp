package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.IngredienteDTO;
import br.ufjf.cookingup.model.entity.Ingrediente;
import br.ufjf.cookingup.model.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public IngredienteDTO findById(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Receita não encontrada com id: " + id));
        return new IngredienteDTO(ingrediente);
    }
}

package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.AlternativaIngredienteDTO;
import br.ufjf.cookingup.model.entity.AlternativaIngrediente;
import br.ufjf.cookingup.model.repository.AlternativaIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlternativaIngredienteService {

    @Autowired
    private AlternativaIngredienteRepository alternativaIngredienteRepository;

    public AlternativaIngredienteDTO findById(Long id) {
        AlternativaIngrediente alterantivaIngrediente = alternativaIngredienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Alternativa   do Ingrediente não encontrada com id: " + id));
        return AlternativaIngredienteDTO.create(alterantivaIngrediente);
    }
}

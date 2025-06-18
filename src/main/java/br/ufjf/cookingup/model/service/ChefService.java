package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.ChefDTO;
import br.ufjf.cookingup.model.entity.Chef;
import br.ufjf.cookingup.model.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    public ChefDTO findById(Long id) {
        Chef chef = chefRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Chef não encontrado com id: " + id));
        return ChefDTO.create(chef);
    }
}

package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.MembroDTO;
import br.ufjf.cookingup.model.entity.Membro;
import br.ufjf.cookingup.model.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    public MembroDTO findById(Long id) {
        Membro membro = membroRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Membro não encontrado com id: " + id));
        return MembroDTO.create(membro);
    }
}

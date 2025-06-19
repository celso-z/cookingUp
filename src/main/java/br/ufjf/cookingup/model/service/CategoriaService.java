package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.CategoriaDTO;
import br.ufjf.cookingup.model.entity.Categoria;
import br.ufjf.cookingup.model.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaDTO buscarPorId(Long id){
            Categoria categoria = categoriaRepository.findById(id)
                    .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada com id: " + id));
            return new CategoriaDTO(categoria);
    }
}

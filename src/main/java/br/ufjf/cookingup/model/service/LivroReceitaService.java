package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.LivroReceitaDTO;
import br.ufjf.cookingup.model.entity.LivroReceita;
import br.ufjf.cookingup.model.repository.LivroReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroReceitaService {

    @Autowired
    private LivroReceitaRepository livroReceitaRepository;

    public LivroReceitaDTO buscarPorId(Long id) {
        LivroReceita livro = livroReceitaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Livro não encontrado com id: " + id));
        return LivroReceitaDTO.create(livro);
    }
}

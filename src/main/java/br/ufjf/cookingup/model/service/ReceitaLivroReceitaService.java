package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.ReceitaLivroReceitaDTO;
import br.ufjf.cookingup.model.entity.ReceitaLivroReceita;
import br.ufjf.cookingup.model.repository.ReceitaLivroReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitaLivroReceitaService {

    @Autowired
    private ReceitaLivroReceitaRepository receitaLivroReceitaRepository;


    public List<ReceitaLivroReceitaDTO> buscarTodos() {
        return receitaLivroReceitaRepository.findByDataFimIsNull()
                .stream()
                .map(ReceitaLivroReceitaDTO::create)
                .collect(Collectors.toList());
    }

    public ReceitaLivroReceita buscarEntidadePorId(Long id) {
        return receitaLivroReceitaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Associação Receita-Livro de Receita não encontrada com id: " + id));
    }

    public ReceitaLivroReceitaDTO buscarDTOporId(Long id) {
        ReceitaLivroReceita receitaLivroReceita = buscarEntidadePorId(id);
        if (receitaLivroReceita.getDataFim() != null) {
            throw new RegraNegocioException("Associação Receita-Livro de Receita com id: " + id + " já foi deletada.");
        }
        return ReceitaLivroReceitaDTO.create(receitaLivroReceita);
    }
}
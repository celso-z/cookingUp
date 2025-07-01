package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.IngredienteReceitaDTO;
import br.ufjf.cookingup.model.entity.IngredienteReceita;
import br.ufjf.cookingup.model.repository.IngredienteReceitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredienteReceitaService {

    @Autowired
    private IngredienteReceitaRepository ingredienteReceitaRepository;

    private ModelMapper modelMapper;

    // Métodos de CRUD (salvar, atualizar, deletar) foram movidos para ReceitaService

    public List<IngredienteReceitaDTO> buscarTodos() {
        return ingredienteReceitaRepository.findByDataFimIsNull()
                .stream()
                .map(IngredienteReceitaDTO::create)
                .collect(Collectors.toList());
    }

    public IngredienteReceita buscarEntidadePorId(Long id) {
        return ingredienteReceitaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Ingrediente da Receita não encontrado com id: " + id));
    }

    public IngredienteReceitaDTO buscarDTOporId(Long id) {
        IngredienteReceita ingredienteReceita = buscarEntidadePorId(id);
        if (ingredienteReceita.getDataFim() != null) {
            throw new RegraNegocioException("Ingrediente da Receita com id: " + id + " já foi deletada.");
        }
        return IngredienteReceitaDTO.create(ingredienteReceita);
    }
}
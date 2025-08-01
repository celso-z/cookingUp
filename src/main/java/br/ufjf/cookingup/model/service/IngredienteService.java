package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.IngredienteDTO;
import br.ufjf.cookingup.model.dto.AlternativaIngredienteDTO;
import br.ufjf.cookingup.model.entity.Ingrediente;
import br.ufjf.cookingup.model.entity.AlternativaIngrediente;
import br.ufjf.cookingup.model.repository.IngredienteRepository;
import br.ufjf.cookingup.model.repository.AlternativaIngredienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private AlternativaIngredienteRepository alternativaIngredienteRepository;

    public Ingrediente converter(IngredienteDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Ingrediente.class);
    }

    public void converterParaEntidade(IngredienteDTO dto, Ingrediente ingrediente) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, ingrediente);
    }

    public IngredienteDTO salvar(IngredienteDTO dto) {
        Ingrediente ingrediente = converter(dto);
        ingrediente.setDataFim(null);
        ingrediente = ingredienteRepository.save(ingrediente);
        return new IngredienteDTO(ingrediente);
    }

    public List<IngredienteDTO> buscarTodos() {
        return ingredienteRepository.findByDataFimIsNull()
                .stream()
                .map(IngredienteDTO::new)
                .collect(Collectors.toList());
    }

    public Ingrediente buscarEntidadePorId(Long id) {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Ingrediente não encontrado com id: " + id));
    }

    public IngredienteDTO buscarDTOporId(Long id) {
        Ingrediente ingrediente = buscarEntidadePorId(id);
        if (ingrediente.getDataFim() != null) {
            throw new RegraNegocioException("Ingrediente com id: " + id + " já foi deletado.");
        }
        return new IngredienteDTO(ingrediente);
    }

    public IngredienteDTO atualizar(Long id, IngredienteDTO dto) {
        Ingrediente ingredienteExistente = buscarEntidadePorId(id);

        if (ingredienteExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar um ingrediente que já foi deletado com id: " + id);
        }

        converterParaEntidade(dto, ingredienteExistente);
        ingredienteExistente.setId(id);

        Ingrediente ingredienteAtualizado = ingredienteRepository.save(ingredienteExistente);
        return new IngredienteDTO(ingredienteAtualizado);
    }

    public void deletar(Long id) {
        Ingrediente ingrediente = buscarEntidadePorId(id);

        if (ingrediente.getDataFim() != null) {
            throw new RegraNegocioException("Ingrediente com id: " + id + " já foi deletado.");
        }

        // Antes de deletar logicamente o ingrediente, desativa suas alternativas
        List<AlternativaIngrediente> alternativasDoIngrediente = alternativaIngredienteRepository.findByIngredienteAndDataFimIsNull(ingrediente);
        alternativasDoIngrediente.forEach(alt -> {
            alt.setDataFim(LocalDate.now());
            alternativaIngredienteRepository.save(alt);
        });

        ingrediente.setDataFim(LocalDate.now());
        ingredienteRepository.save(ingrediente);
    }

    // --- Métodos para gerenciar Alternativas ---

    public AlternativaIngredienteDTO adicionarAlternativa(Long idIngredientePrincipal, Long idAlternativa) {
        Ingrediente ingredientePrincipal = buscarEntidadePorId(idIngredientePrincipal);
        Ingrediente alternativa = buscarEntidadePorId(idAlternativa);

        if (ingredientePrincipal.equals(alternativa)) {
            throw new RegraNegocioException("Um ingrediente não pode ser alternativa de si mesmo.");
        }

        alternativaIngredienteRepository.findByIngredienteAndAlternativaAndDataFimIsNull(ingredientePrincipal, alternativa)
                .ifPresent(a -> { throw new RegraNegocioException("Esta alternativa já foi cadastrada para este ingrediente."); });
        alternativaIngredienteRepository.findByIngredienteAndAlternativaAndDataFimIsNull(alternativa, ingredientePrincipal)
                .ifPresent(a -> { throw new RegraNegocioException("Este ingrediente já é alternativa para o outro ingrediente."); });

        AlternativaIngrediente novaAlternativa = new AlternativaIngrediente();
        novaAlternativa.setIngrediente(ingredientePrincipal);
        novaAlternativa.setAlternativa(alternativa);
        novaAlternativa.setDataFim(null);

        novaAlternativa = alternativaIngredienteRepository.save(novaAlternativa);
        return AlternativaIngredienteDTO.create(novaAlternativa);
    }

    public void removerAlternativa(Long idAlternativaIngrediente) {
        AlternativaIngrediente alternativaIngrediente = alternativaIngredienteRepository.findById(idAlternativaIngrediente)
                .orElseThrow(() -> new RegraNegocioException("Alternativa de ingrediente não encontrada com id: " + idAlternativaIngrediente));

        if (alternativaIngrediente.getDataFim() != null) {
            throw new RegraNegocioException("Alternativa de ingrediente com id: " + idAlternativaIngrediente + " já foi deletada.");
        }

        alternativaIngrediente.setDataFim(LocalDate.now());
        alternativaIngredienteRepository.save(alternativaIngrediente);
    }
}
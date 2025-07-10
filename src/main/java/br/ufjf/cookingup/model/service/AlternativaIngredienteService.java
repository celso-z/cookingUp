package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.AlternativaIngredienteDTO;
import br.ufjf.cookingup.model.entity.AlternativaIngrediente;
import br.ufjf.cookingup.model.entity.Ingrediente;
import br.ufjf.cookingup.model.repository.AlternativaIngredienteRepository;
import br.ufjf.cookingup.model.repository.IngredienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlternativaIngredienteService {

    @Autowired
    private AlternativaIngredienteRepository alternativaIngredienteRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public AlternativaIngredienteDTO salvar(AlternativaIngredienteDTO dto) {
        AlternativaIngrediente alternativaIngrediente = new AlternativaIngrediente();
        alternativaIngrediente.setDataInicio(LocalDate.now());
        alternativaIngrediente.setDataFim(null);

        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIdIngrediente())
                .orElseThrow(() -> new RegraNegocioException("Ingrediente não encontrado com id: " + dto.getIdIngrediente()));
        alternativaIngrediente.setIngrediente(ingrediente);

        Ingrediente alternativa = ingredienteRepository.findById(dto.getIdAlternativa())
                .orElseThrow(() -> new RegraNegocioException("Alternativa não encontrada com id: " + dto.getIdAlternativa()));
        alternativaIngrediente.setAlternativa(alternativa);

        alternativaIngrediente = alternativaIngredienteRepository.save(alternativaIngrediente);
        return AlternativaIngredienteDTO.create(alternativaIngrediente);
    }

    public List<AlternativaIngredienteDTO> buscarTodos() {
        return alternativaIngredienteRepository.findAll()
                .stream()
                .filter(alt -> alt.getDataFim() == null)
                .map(AlternativaIngredienteDTO::create)
                .collect(Collectors.toList());
    }

    public AlternativaIngrediente buscarEntidadePorId(Long id) {
        return alternativaIngredienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Alternativa do Ingrediente não encontrada com id: " + id));
    }

    public AlternativaIngredienteDTO buscarDTOporId(Long id) {
        AlternativaIngrediente alternativaIngrediente = buscarEntidadePorId(id);
        if (alternativaIngrediente.getDataFim() != null) {
            throw new RegraNegocioException("Alternativa de Ingrediente com id: " + id + " já foi deletada.");
        }
        return AlternativaIngredienteDTO.create(alternativaIngrediente);
    }

    public AlternativaIngredienteDTO atualizar(Long id, AlternativaIngredienteDTO dto) {
        AlternativaIngrediente alternativaExistente = buscarEntidadePorId(id);

        if (alternativaExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar uma alternativa de ingrediente que já foi deletada com id: " + id);
        }

        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIdIngrediente())
                .orElseThrow(() -> new RegraNegocioException("Ingrediente não encontrado com id: " + dto.getIdIngrediente()));
        alternativaExistente.setIngrediente(ingrediente);

        Ingrediente alternativa = ingredienteRepository.findById(dto.getIdAlternativa())
                .orElseThrow(() -> new RegraNegocioException("Alternativa não encontrada com id: " + dto.getIdAlternativa()));
        alternativaExistente.setAlternativa(alternativa);

        AlternativaIngrediente alternativaAtualizada = alternativaIngredienteRepository.save(alternativaExistente);
        return AlternativaIngredienteDTO.create(alternativaAtualizada);
    }

    public void deletar(Long id) {
        AlternativaIngrediente alternativaIngrediente = buscarEntidadePorId(id);

        if (alternativaIngrediente.getDataFim() != null) {
            throw new RegraNegocioException("Alternativa de Ingrediente com id: " + id + " já foi deletada.");
        }

        alternativaIngrediente.setDataFim(LocalDate.now());
        alternativaIngredienteRepository.save(alternativaIngrediente);
    }
}
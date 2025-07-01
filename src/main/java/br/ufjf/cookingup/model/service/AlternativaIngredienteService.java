package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.AlternativaIngredienteDTO;
import br.ufjf.cookingup.model.entity.AlternativaIngrediente;
import br.ufjf.cookingup.model.repository.AlternativaIngredienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlternativaIngredienteService {

    @Autowired
    private AlternativaIngredienteRepository alternativaIngredienteRepository;

    private ModelMapper modelMapper;


    public List<AlternativaIngredienteDTO> buscarTodos() {
        return alternativaIngredienteRepository.findAll()
                .stream()
                .map(AlternativaIngredienteDTO::create)
                .collect(Collectors.toList());
    }

    public AlternativaIngrediente buscarEntidadePorId(Long id) {
        return alternativaIngredienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Alternativa do Ingrediente não encontrada com id: " + id));
    }

    public AlternativaIngredienteDTO buscarDTOporId(Long id) {
        AlternativaIngrediente alternativaIngrediente = buscarEntidadePorId(id);
        return AlternativaIngredienteDTO.create(alternativaIngrediente);
    }
}
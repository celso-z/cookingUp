package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.ChefDTO;
import br.ufjf.cookingup.model.entity.Chef;
import br.ufjf.cookingup.model.repository.ChefRepository;
import br.ufjf.cookingup.model.validator.ChefValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    @Autowired
    private ChefValidator validator;

    public Chef converter(ChefDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Chef.class);
    }

    public void converterParaEntidade(ChefDTO dto, Chef chef) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, chef);
    }

    public ChefDTO salvar(ChefDTO dto) {
        validator.validar(dto);
        Chef chef = converter(dto);
        chef.setDataCadastro(LocalDate.now());
        chef.setDataFim(null);
        chef = chefRepository.save(chef);
        return ChefDTO.create(chef);
    }

    public List<ChefDTO> buscarTodos() {
        return chefRepository.findByDataFimIsNull()
                .stream()
                .map(ChefDTO::create)
                .collect(Collectors.toList());
    }

    public Chef buscarEntidadePorId(Long id) {
        return chefRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Chef não encontrado com id: " + id));
    }

    public ChefDTO buscarDTOporId(Long id) {
        Chef chef = buscarEntidadePorId(id);
        if (chef.getDataFim() != null) {
            throw new RegraNegocioException("Chef com id: " + id + " já foi deletado.");
        }
        return ChefDTO.create(chef);
    }

    public ChefDTO atualizar(Long id, ChefDTO dto) {
        validator.validar(dto);
        Chef chefExistente = buscarEntidadePorId(id);

        if (chefExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar um chef que já foi deletado com id: " + id);
        }

        converterParaEntidade(dto, chefExistente);
        chefExistente.setId(id);

        Chef chefAtualizado = chefRepository.save(chefExistente);
        return ChefDTO.create(chefAtualizado);
    }

    public void deletar(Long id) {
        Chef chef = buscarEntidadePorId(id);

        if (chef.getDataFim() != null) {
            throw new RegraNegocioException("Chef com id: " + id + " já foi deletado.");
        }

        chef.setDataFim(LocalDate.now());
        chefRepository.save(chef);
    }
}
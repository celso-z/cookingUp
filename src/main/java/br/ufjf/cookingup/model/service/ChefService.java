package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.ChefDTO;
import br.ufjf.cookingup.model.entity.Chef;
import br.ufjf.cookingup.model.repository.ChefRepository;
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

    private ModelMapper modelMapper;

    public ChefDTO salvar(ChefDTO dto) {
        Chef chef = modelMapper.map(dto, Chef.class);
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
        Chef chefExistente = buscarEntidadePorId(id);

        if (chefExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar um chef que já foi deletado com id: " + id);
        }

        modelMapper.map(dto, chefExistente);
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
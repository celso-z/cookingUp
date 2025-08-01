package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.MembroDTO;
import br.ufjf.cookingup.model.entity.Membro;
import br.ufjf.cookingup.model.repository.MembroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    public Membro converter(MembroDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Membro.class);
    }

    public void converterParaEntidade(MembroDTO dto, Membro membro) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, membro);
    }

    public MembroDTO salvar(MembroDTO dto) {
        Membro membro = converter(dto);
        membro.setDataCadastro(LocalDate.now());
        membro.setDataFim(null);
        membro = membroRepository.save(membro);
        return MembroDTO.create(membro);
    }

    public List<MembroDTO> buscarTodos() {
        return membroRepository.findByDataFimIsNull()
                .stream()
                .map(MembroDTO::create)
                .collect(Collectors.toList());
    }

    public Membro buscarEntidadePorId(Long id) {
        return membroRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Membro não encontrado com id: " + id));
    }

    public MembroDTO buscarDTOporId(Long id) {
        Membro membro = buscarEntidadePorId(id);
        if (membro.getDataFim() != null) {
            throw new RegraNegocioException("Membro com id: " + id + " já foi deletado.");
        }
        return MembroDTO.create(membro);
    }

    public MembroDTO atualizar(Long id, MembroDTO dto) {
        Membro membroExistente = buscarEntidadePorId(id);

        if (membroExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar um membro que já foi deletado com id: " + id);
        }

        converterParaEntidade(dto, membroExistente);
        membroExistente.setId(id);

        Membro membroAtualizado = membroRepository.save(membroExistente);
        return MembroDTO.create(membroAtualizado);
    }

    public void deletar(Long id) {
        Membro membro = buscarEntidadePorId(id);

        if (membro.getDataFim() != null) {
            throw new RegraNegocioException("Membro com id: " + id + " já foi deletado.");
        }

        membro.setDataFim(LocalDate.now());
        membroRepository.save(membro);
    }
}
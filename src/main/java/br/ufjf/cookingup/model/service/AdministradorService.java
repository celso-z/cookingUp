package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.AdministradorDTO;
import br.ufjf.cookingup.model.entity.Administrador;
import br.ufjf.cookingup.model.repository.AdministradorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    private ModelMapper modelMapper;

    public AdministradorDTO salvar(AdministradorDTO dto) {
        Administrador administrador = modelMapper.map(dto, Administrador.class);
        administrador.setDataCadastro(LocalDate.now());
        administrador.setDataFim(null);
        administrador = administradorRepository.save(administrador);
        return AdministradorDTO.create(administrador);
    }

    public List<AdministradorDTO> buscarTodos() {
        return administradorRepository.findByDataFimIsNull()
                .stream()
                .map(AdministradorDTO::create)
                .collect(Collectors.toList());
    }

    public Administrador buscarPorId(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Administrador não encontrado com id: " + id));
    }

    public AdministradorDTO buscarDTOporId(Long id) {
        Administrador administrador = buscarPorId(id);
        return AdministradorDTO.create(administrador);
    }

    public AdministradorDTO atualizar(Long id, AdministradorDTO dto) {
        Administrador administradorExistente = buscarPorId(id);

        if (administradorExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar um administrador que já foi deletado com id: " + id);
        }

        modelMapper.map(dto, administradorExistente);
        administradorExistente.setId(id);

        Administrador administradorAtualizado = administradorRepository.save(administradorExistente);
        return AdministradorDTO.create(administradorAtualizado);
    }

    public void deletar(Long id) {
        Administrador administrador = buscarPorId(id);

        if (administrador.getDataFim() != null) {
            throw new RegraNegocioException("Administrador com id: " + id + " já foi deletado.");
        }

        administrador.setDataFim(LocalDate.now());
        administradorRepository.save(administrador);
    }
}
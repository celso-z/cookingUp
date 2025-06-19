package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.AdministradorDTO;
import br.ufjf.cookingup.model.entity.Administrador;
import br.ufjf.cookingup.model.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    @Autowired
    AdministradorRepository administradorRepository;

    public AdministradorDTO buscarPorId(Long id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Administrador não encontrada com id: " + id));
        return AdministradorDTO.create(administrador);
    }
}

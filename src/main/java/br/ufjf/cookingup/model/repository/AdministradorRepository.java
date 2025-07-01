package br.ufjf.cookingup.model.repository;

import br.ufjf.cookingup.model.dto.AdministradorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import br.ufjf.cookingup.model.entity.Administrador;

import java.util.Arrays;
import java.util.List;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    List<Administrador> findByDataFimIsNull();
}

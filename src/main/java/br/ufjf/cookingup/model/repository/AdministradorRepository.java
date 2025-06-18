package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufjf.cookingup.model.entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

}

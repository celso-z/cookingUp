package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

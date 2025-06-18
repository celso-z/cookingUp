package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.Membro;

public interface MembroRepository extends JpaRepository<Membro, Long> {

}

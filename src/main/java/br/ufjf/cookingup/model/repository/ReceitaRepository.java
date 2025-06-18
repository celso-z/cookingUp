package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}

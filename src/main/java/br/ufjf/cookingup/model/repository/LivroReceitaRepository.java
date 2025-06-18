package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.LivroReceita;

public interface LivroReceitaRepository extends JpaRepository<LivroReceita, Long> {

}

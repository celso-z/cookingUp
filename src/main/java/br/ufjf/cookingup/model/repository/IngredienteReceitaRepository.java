package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.IngredienteReceita;

public interface IngredienteReceitaRepository extends JpaRepository<IngredienteReceita, Long> {

}

package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

}

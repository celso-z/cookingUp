package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.Ingrediente;

import java.util.Arrays;
import java.util.List;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

    List<Ingrediente> findByDataFimIsNull();
}

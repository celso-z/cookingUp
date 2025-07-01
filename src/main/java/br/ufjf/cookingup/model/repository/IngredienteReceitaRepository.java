package br.ufjf.cookingup.model.repository;

import br.ufjf.cookingup.model.entity.Ingrediente;
import br.ufjf.cookingup.model.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.IngredienteReceita;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface IngredienteReceitaRepository extends JpaRepository<IngredienteReceita, Long> {

    List<IngredienteReceita> findByDataFimIsNull();

    Optional<IngredienteReceita> findByReceitaAndIngredienteAndDataFimIsNull(Receita receita, Ingrediente ingrediente);

    List<IngredienteReceita> findByReceitaAndDataFimIsNull(Receita receita);
}

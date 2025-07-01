package br.ufjf.cookingup.model.repository;

import br.ufjf.cookingup.model.entity.AlternativaIngrediente;
import br.ufjf.cookingup.model.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlternativaIngredienteRepository extends JpaRepository<AlternativaIngrediente, Long> {
    Optional<Object> findByIngredienteAndAlternativaAndDataFimIsNull(Ingrediente alternativa, Ingrediente ingredientePrincipal);

    List<AlternativaIngrediente> findByIngredienteAndDataFimIsNull(Ingrediente ingrediente);
}

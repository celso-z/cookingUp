package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.Chef;

import java.util.Arrays;
import java.util.List;

public interface ChefRepository extends JpaRepository<Chef, Long> {

    List<Chef> findByDataFimIsNull();
}

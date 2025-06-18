package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.Chef;

public interface ChefRepository extends JpaRepository<Chef, Long> {

}

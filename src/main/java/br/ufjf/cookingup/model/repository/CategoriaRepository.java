package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}

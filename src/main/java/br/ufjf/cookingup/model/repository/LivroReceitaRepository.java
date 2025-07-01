package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.LivroReceita;

import java.util.List;
import java.util.Optional;

public interface LivroReceitaRepository extends JpaRepository<LivroReceita, Long> {

    List<LivroReceita> findByDataFimIsNull();
}

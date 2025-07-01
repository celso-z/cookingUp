package br.ufjf.cookingup.model.repository;

import br.ufjf.cookingup.model.entity.LivroReceita;
import br.ufjf.cookingup.model.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.ReceitaLivroReceita;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ReceitaLivroReceitaRepository extends JpaRepository<ReceitaLivroReceita, Long> {

    List<ReceitaLivroReceita> findByLivroAndDataFimIsNull(LivroReceita livro);

    Optional<ReceitaLivroReceita> findByLivroAndReceitaAndDataFimIsNull(LivroReceita livro, Receita receita);

    List<ReceitaLivroReceita> findByDataFimIsNull();
}

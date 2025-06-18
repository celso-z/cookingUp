package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}

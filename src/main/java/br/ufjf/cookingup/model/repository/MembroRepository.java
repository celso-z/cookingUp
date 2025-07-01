package br.ufjf.cookingup.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.cookingup.model.entity.Membro;

import java.util.Arrays;
import java.util.List;

public interface MembroRepository extends JpaRepository<Membro, Long> {

    List<Membro> findByDataFimIsNull();
}

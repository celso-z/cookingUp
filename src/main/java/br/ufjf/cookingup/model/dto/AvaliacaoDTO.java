package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Membro;
import br.ufjf.cookingup.model.entity.Receita;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
public class AvaliacaoDTO {
    private Long id;
    private Integer nota;
    private String consideracoes;
    private Date data;
    private Membro avaliador;
    private Receita receitaAvaliada;
}

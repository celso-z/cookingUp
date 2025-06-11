package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
public class ReceitaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Integer porcoes;
    private String modoPreparo;
    private Integer tempoPreparo;
    private String imagemUrl;
    private Date dataCadastro;
    private Date dataFim;
    //private ArrayList<IngredienteReceita> ingredientesReceita;
    private Categoria categoria;
}

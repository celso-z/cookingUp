package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Receita;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.sql.Date;
import java.time.LocalDate;

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
    private LocalDate dataCadastro;
    private LocalDate dataFim;
    private Long idCategoria;
    private String nomeCategoria;
    // private ArrayList<IngredienteReceita> ingredientesReceita;

    public static ReceitaDTO create(Receita receita) {
        ModelMapper modelMapper = new ModelMapper();
        ReceitaDTO dto = modelMapper.map(receita, ReceitaDTO.class);
        if (receita.getCategoria() != null) {
            dto.idCategoria = receita.getCategoria().getId();
            dto.nomeCategoria = receita.getCategoria().getNome();
        }
        return dto;
    }
}
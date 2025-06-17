package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.IngredienteReceita;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class IngredienteReceitaDTO {
    private Long id;
    private Integer quantidade;
    private String observacoes;
    private String unidade;
    private Long idIngrediente;
    private String nomeIngrediente;

    public static IngredienteReceitaDTO create(IngredienteReceita ingredienteReceita) {
        ModelMapper modelMapper = new ModelMapper();
        IngredienteReceitaDTO dto = modelMapper.map(ingredienteReceita, IngredienteReceitaDTO.class);

        if (ingredienteReceita.getIngrediente() != null) {
            dto.setIdIngrediente(ingredienteReceita.getIngrediente().getId());
            dto.setNomeIngrediente(ingredienteReceita.getIngrediente().getNome());
        }

        return dto;
    }
}

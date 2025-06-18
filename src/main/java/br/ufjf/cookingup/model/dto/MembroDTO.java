package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Membro;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MembroDTO extends UsuarioDTO {
    // O campo 'id' é herdado de UsuarioDTO
    private Integer nivel;
    // private ArrayList<LivroReceita> livrosReceitas;
    // private ArrayList<Receita> receitasFavoritas;

    public static MembroDTO create(Membro membro) {
        ModelMapper modelMapper = new ModelMapper();
        MembroDTO dto = modelMapper.map(membro, MembroDTO.class);
        return dto;
    }
}

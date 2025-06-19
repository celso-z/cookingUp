package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Chef;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChefDTO extends UsuarioDTO {
    private String biografia;
    private List<ReceitaDTO> receitas;

    public static ChefDTO create(Chef chef) {
        ModelMapper modelMapper = new ModelMapper();
        ChefDTO dto = modelMapper.map(chef, ChefDTO.class);
        if (chef.getReceitas() != null) {
            dto.setReceitas(chef.getReceitas().stream()
                    .map(ReceitaDTO::create)
                    .collect(Collectors.toList()));
        } else {
            dto.setReceitas(new ArrayList<>());
        }
        return dto;
    }
}

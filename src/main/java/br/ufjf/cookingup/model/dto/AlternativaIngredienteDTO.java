package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.AlternativaIngrediente;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class AlternativaIngredienteDTO {
    private Long id;
    private Long idIngrediente;
    private Long idAlternativa;
    private String nomeIngrediente;
    private String nomeAlternativa;



    public static AlternativaIngredienteDTO create(AlternativaIngrediente ingrediente) {
        ModelMapper modelMapper = new ModelMapper();
        AlternativaIngredienteDTO dto = modelMapper.map(ingrediente, AlternativaIngredienteDTO.class);
        dto.nomeIngrediente = ingrediente.getIngrediente().getNome();;
        dto.nomeAlternativa = ingrediente.getAlternativa().getNome();
        return dto;
    }
}


package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.LivroReceita;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class LivroReceitaDTO {
    private Long id;
    private String titulo;
    private Long idProprietario;

    public static LivroReceitaDTO create(LivroReceita livro) {
        ModelMapper modelMapper = new ModelMapper();
        LivroReceitaDTO dto = modelMapper.map(livro, LivroReceitaDTO.class);
        if (livro.getProprietario() != null) {
            dto.idProprietario = livro.getProprietario().getId();
        }
        return dto;
    }
}
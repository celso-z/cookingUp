package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.ReceitaLivroReceita;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class ReceitaLivroReceitaDTO {
    private Long id;
    private Long idLivro;
    private String tituloLivro;
    private Long idReceita;
    private String tituloReceita;

    public static ReceitaLivroReceitaDTO create(ReceitaLivroReceita receitaLivroReceita) {
        ModelMapper modelMapper = new ModelMapper();
        ReceitaLivroReceitaDTO dto = modelMapper.map(receitaLivroReceita, ReceitaLivroReceitaDTO.class);

        if (receitaLivroReceita.getLivro() != null) {
            dto.setIdLivro(receitaLivroReceita.getLivro().getId());
            dto.setTituloLivro(receitaLivroReceita.getLivro().getTitulo());
        }
        if (receitaLivroReceita.getReceita() != null) {
            dto.setIdReceita(receitaLivroReceita.getReceita().getId());
            dto.setTituloReceita(receitaLivroReceita.getReceita().getTitulo());
        }
        return dto;
    }
}

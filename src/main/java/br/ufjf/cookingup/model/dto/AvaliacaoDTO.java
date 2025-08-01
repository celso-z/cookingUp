package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Avaliacao;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AvaliacaoDTO {
    private Long id;
    private Integer nota;
    private String consideracoes;
    private Long idAvaliador;
    private Long idReceitaAvaliada;

    public static AvaliacaoDTO create(Avaliacao avaliacao) {
        ModelMapper modelMapper = new ModelMapper();
        AvaliacaoDTO dto = modelMapper.map(avaliacao, AvaliacaoDTO.class);
        if (avaliacao.getAvaliador() != null) {
            dto.idAvaliador = avaliacao.getAvaliador().getId();
        }
        if (avaliacao.getReceitaAvaliada() != null) {
            dto.idReceitaAvaliada = avaliacao.getReceitaAvaliada().getId();
        }
        return dto;
    }
}
package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Avaliacao;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.sql.Date;

@Data
@NoArgsConstructor
public class AvaliacaoDTO {
    private Long id;
    private Integer nota;
    private String consideracoes;
    private Date data;
    private Long idAvaliador;
    private String nomeAvaliador;
    private Long idReceitaAvaliada;
    private String tituloReceitaAvaliada;

    public static AvaliacaoDTO create(Avaliacao avaliacao) {
        ModelMapper modelMapper = new ModelMapper();
        AvaliacaoDTO dto = modelMapper.map(avaliacao, AvaliacaoDTO.class);
        if (avaliacao.getAvaliador() != null) {
            dto.idAvaliador = avaliacao.getAvaliador().getId();
            dto.nomeAvaliador = avaliacao.getAvaliador().getNome();
        }
        if (avaliacao.getReceitaAvaliada() != null) {
            dto.idReceitaAvaliada = avaliacao.getReceitaAvaliada().getId();
            dto.tituloReceitaAvaliada = avaliacao.getReceitaAvaliada().getTitulo();
        }
        return dto;
    }
}
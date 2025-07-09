package br.ufjf.cookingup.model.service;


import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.AvaliacaoDTO;
import br.ufjf.cookingup.model.entity.Avaliacao;
import br.ufjf.cookingup.model.entity.Membro;
import br.ufjf.cookingup.model.entity.Receita;
import br.ufjf.cookingup.model.repository.AvaliacaoRepository;
import br.ufjf.cookingup.model.repository.MembroRepository;
import br.ufjf.cookingup.model.repository.ReceitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    private ModelMapper modelMapper;

    public AvaliacaoDTO salvar(AvaliacaoDTO dto) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(dto.getNota());
        avaliacao.setConsideracoes(dto.getConsideracoes());
        avaliacao.setDataInicio(LocalDate.now());
        avaliacao.setDataFim(null);

        Membro avaliador = membroRepository.findById(dto.getIdAvaliador())
                .orElseThrow(() -> new RegraNegocioException("Avaliador não encontrado com id: " + dto.getIdAvaliador()));
        avaliacao.setAvaliador(avaliador);

        Receita receitaAvaliada = receitaRepository.findById(dto.getIdReceitaAvaliada())
                .orElseThrow(() -> new RegraNegocioException("Receita avaliada não encontrada com id: " + dto.getIdReceitaAvaliada()));
        avaliacao.setReceitaAvaliada(receitaAvaliada);

        avaliacao = avaliacaoRepository.save(avaliacao);
        return AvaliacaoDTO.create(avaliacao);
    }

    public List<AvaliacaoDTO> buscarTodos() {
        return avaliacaoRepository.findByDataFimIsNull()
                .stream()
                .map(AvaliacaoDTO::create)
                .collect(Collectors.toList());
    }

    public Avaliacao buscarEntidadePorId(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Avaliação não encontrada com id: " + id));
    }

    public AvaliacaoDTO buscarDTOporId(Long id) {
        Avaliacao avaliacao = buscarEntidadePorId(id);
        if (avaliacao.getDataFim() != null) {
            throw new RegraNegocioException("Avaliação com id: " + id + " já foi deletada.");
        }
        return AvaliacaoDTO.create(avaliacao);
    }

    public AvaliacaoDTO atualizar(Long id, AvaliacaoDTO dto) {
        Avaliacao avaliacaoExistente = buscarEntidadePorId(id);

        if (avaliacaoExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar uma avaliação que já foi deletada com id: " + id);
        }

        avaliacaoExistente.setNota(dto.getNota());
        avaliacaoExistente.setConsideracoes(dto.getConsideracoes());

        Membro avaliador = membroRepository.findById(dto.getIdAvaliador())
                .orElseThrow(() -> new RegraNegocioException("Avaliador não encontrado com id: " + dto.getIdAvaliador()));
        avaliacaoExistente.setAvaliador(avaliador);

        Receita receitaAvaliada = receitaRepository.findById(dto.getIdReceitaAvaliada())
                .orElseThrow(() -> new RegraNegocioException("Receita avaliada não encontrada com id: " + dto.getIdReceitaAvaliada()));
        avaliacaoExistente.setReceitaAvaliada(receitaAvaliada);

        Avaliacao avaliacaoAtualizada = avaliacaoRepository.save(avaliacaoExistente);
        return AvaliacaoDTO.create(avaliacaoAtualizada);
    }

    public void deletar(Long id) {
        Avaliacao avaliacao = buscarEntidadePorId(id);

        if (avaliacao.getDataFim() != null) {
            throw new RegraNegocioException("Avaliação com id: " + id + " já foi deletada.");
        }

        avaliacao.setDataFim(LocalDate.now());
        avaliacaoRepository.save(avaliacao);
    }
}
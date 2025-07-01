package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.LivroReceitaDTO;
import br.ufjf.cookingup.model.dto.ReceitaLivroReceitaDTO;
import br.ufjf.cookingup.model.entity.LivroReceita;
import br.ufjf.cookingup.model.entity.Membro;
import br.ufjf.cookingup.model.entity.Receita;
import br.ufjf.cookingup.model.entity.ReceitaLivroReceita;
import br.ufjf.cookingup.model.repository.LivroReceitaRepository;
import br.ufjf.cookingup.model.repository.MembroRepository;
import br.ufjf.cookingup.model.repository.ReceitaRepository;
import br.ufjf.cookingup.model.repository.ReceitaLivroReceitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroReceitaService {

    @Autowired
    private LivroReceitaRepository livroReceitaRepository;

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ReceitaLivroReceitaRepository receitaLivroReceitaRepository;

    private ModelMapper modelMapper;

    public LivroReceitaDTO salvar(LivroReceitaDTO dto) {
        LivroReceita livroReceita = new LivroReceita();
        livroReceita.setTitulo(dto.getTitulo());

        Membro proprietario = membroRepository.findById(dto.getIdProprietario())
                .orElseThrow(() -> new RegraNegocioException("Proprietário não encontrado com id: " + dto.getIdProprietario()));
        livroReceita.setProprietario(proprietario);

        livroReceita.setDataFim(null);
        livroReceita = livroReceitaRepository.save(livroReceita);
        return LivroReceitaDTO.create(livroReceita);
    }

    public List<LivroReceitaDTO> buscarTodos() {
        return livroReceitaRepository.findByDataFimIsNull()
                .stream()
                .map(LivroReceitaDTO::create)
                .collect(Collectors.toList());
    }

    public LivroReceita buscarEntidadePorId(Long id) {
        return livroReceitaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Livro não encontrado com id: " + id));
    }

    public LivroReceitaDTO buscarDTOporId(Long id) {
        LivroReceita livro = buscarEntidadePorId(id);
        if (livro.getDataFim() != null) {
            throw new RegraNegocioException("Livro com id: " + id + " já foi deletado.");
        }
        return LivroReceitaDTO.create(livro);
    }

    public LivroReceitaDTO atualizar(Long id, LivroReceitaDTO dto) {
        LivroReceita livroExistente = buscarEntidadePorId(id);

        if (livroExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar um livro que já foi deletado com id: " + id);
        }

        livroExistente.setTitulo(dto.getTitulo());

        Membro proprietario = membroRepository.findById(dto.getIdProprietario())
                .orElseThrow(() -> new RegraNegocioException("Proprietário não encontrado com id: " + dto.getIdProprietario()));
        livroExistente.setProprietario(proprietario);

        LivroReceita livroAtualizado = livroReceitaRepository.save(livroExistente);
        return LivroReceitaDTO.create(livroAtualizado);
    }

    public void deletar(Long id) {
        LivroReceita livro = buscarEntidadePorId(id);

        if (livro.getDataFim() != null) {
            throw new RegraNegocioException("Livro com id: " + id + " já foi deletado.");
        }

        // Deleção lógica de ReceitasLivroReceita relacionadas
        List<ReceitaLivroReceita> receitasNoLivro = receitaLivroReceitaRepository.findByLivroAndDataFimIsNull(livro);
        receitasNoLivro.forEach(rlr -> {
            rlr.setDataFim(LocalDate.now());
            receitaLivroReceitaRepository.save(rlr);
        });

        livro.setDataFim(LocalDate.now());
        livroReceitaRepository.save(livro);
    }

    // --- Métodos para gerenciar Receitas no Livro ---

    public ReceitaLivroReceitaDTO adicionarReceitaNoLivro(Long idLivro, Long idReceita) {
        LivroReceita livro = buscarEntidadePorId(idLivro);
        Receita receita = receitaRepository.findById(idReceita)
                .orElseThrow(() -> new RegraNegocioException("Receita não encontrada com id: " + idReceita));

        Optional<ReceitaLivroReceita> existingRel = receitaLivroReceitaRepository
                .findByLivroAndReceitaAndDataFimIsNull(livro, receita);

        if (existingRel.isPresent()) {
            throw new RegraNegocioException("Esta receita já está neste livro.");
        }

        ReceitaLivroReceita novaReceitaLivro = new ReceitaLivroReceita();
        novaReceitaLivro.setLivro(livro);
        novaReceitaLivro.setReceita(receita);
        novaReceitaLivro.setDataFim(null);

        novaReceitaLivro = receitaLivroReceitaRepository.save(novaReceitaLivro);
        return ReceitaLivroReceitaDTO.create(novaReceitaLivro);
    }

    public void removerReceitaDoLivro(Long idLivro, Long idReceitaLivroReceita) {
        LivroReceita livro = buscarEntidadePorId(idLivro);

        ReceitaLivroReceita receitaLivroReceita = receitaLivroReceitaRepository.findById(idReceitaLivroReceita)
                .orElseThrow(() -> new RegraNegocioException("Associação Receita-Livro de Receita não encontrada com id: " + idReceitaLivroReceita));

        if (receitaLivroReceita.getDataFim() != null) {
            throw new RegraNegocioException("Associação Receita-Livro de Receita com id: " + idReceitaLivroReceita + " já foi deletada.");
        }

        if (!receitaLivroReceita.getLivro().getId().equals(idLivro)) {
            throw new RegraNegocioException("A associação não pertence ao livro informado.");
        }

        receitaLivroReceita.setDataFim(LocalDate.now());
        receitaLivroReceitaRepository.save(receitaLivroReceita);
    }
}
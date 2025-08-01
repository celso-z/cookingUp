package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.ReceitaDTO;
import br.ufjf.cookingup.model.dto.IngredienteReceitaDTO;
import br.ufjf.cookingup.model.entity.Categoria;
import br.ufjf.cookingup.model.entity.Receita;
import br.ufjf.cookingup.model.entity.Ingrediente;
import br.ufjf.cookingup.model.entity.IngredienteReceita;
import br.ufjf.cookingup.model.repository.CategoriaRepository;
import br.ufjf.cookingup.model.repository.ReceitaRepository;
import br.ufjf.cookingup.model.repository.IngredienteRepository;
import br.ufjf.cookingup.model.repository.IngredienteReceitaRepository;
import br.ufjf.cookingup.model.validator.ReceitaValidator;
import br.ufjf.cookingup.model.validator.IngredienteReceitaValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private IngredienteReceitaRepository ingredienteReceitaRepository;

    @Autowired
    private ReceitaValidator validator;

    @Autowired
    private IngredienteReceitaValidator ingredienteReceitaValidator;

    public Receita converter(ReceitaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Receita.class);
    }

    public void converterParaEntidade(ReceitaDTO dto, Receita receita) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, receita);
    }

    public IngredienteReceita converterIngredienteReceita(IngredienteReceitaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, IngredienteReceita.class);
    }

    public ReceitaDTO salvar(ReceitaDTO dto) {
        validator.validar(dto);
        Receita receita = converter(dto);
        receita.setDataCadastro(LocalDate.now());
        receita.setDataFim(null);

        if (dto.getIdCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                    .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada com id: " + dto.getIdCategoria()));
            receita.setCategoria(categoria);
        }

        receita = receitaRepository.save(receita);
        return ReceitaDTO.create(receita);
    }

    public List<ReceitaDTO> buscarTodos() {
        return receitaRepository.findByDataFimIsNull()
                .stream()
                .map(ReceitaDTO::create)
                .collect(Collectors.toList());
    }

    public Receita buscarEntidadePorId(Long id) {
        return receitaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Receita não encontrada com id: " + id));
    }

    public ReceitaDTO buscarDTOporId(Long id) {
        Receita receita = buscarEntidadePorId(id);
        if (receita.getDataFim() != null) {
            throw new RegraNegocioException("Receita com id: " + id + " já foi deletada.");
        }
        return ReceitaDTO.create(receita);
    }

    public ReceitaDTO atualizar(Long id, ReceitaDTO dto) {
        validator.validar(dto);
        Receita receitaExistente = buscarEntidadePorId(id);

        if (receitaExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar uma receita que já foi deletada com id: " + id);
        }

        converterParaEntidade(dto, receitaExistente);
        receitaExistente.setId(id);

        if (dto.getIdCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                    .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada com id: " + dto.getIdCategoria()));
            receitaExistente.setCategoria(categoria);
        } else {
            receitaExistente.setCategoria(null);
        }

        Receita receitaAtualizada = receitaRepository.save(receitaExistente);
        return ReceitaDTO.create(receitaAtualizada);
    }

    public void deletar(Long id) {
        Receita receita = buscarEntidadePorId(id);

        if (receita.getDataFim() != null) {
            throw new RegraNegocioException("Receita com id: " + id + " já foi deletada.");
        }

        // Deleção lógica de IngredientesReceita relacionados
        List<IngredienteReceita> ingredientesDaReceita = ingredienteReceitaRepository.findByReceitaAndDataFimIsNull(receita);
        ingredientesDaReceita.forEach(ir -> {
            ir.setDataFim(LocalDate.now());
            ingredienteReceitaRepository.save(ir);
        });

        receita.setDataFim(LocalDate.now());
        receitaRepository.save(receita);
    }

    // --- Métodos para gerenciar Ingredientes da Receita ---

    public IngredienteReceitaDTO adicionarIngredienteNaReceita(Long idReceita, IngredienteReceitaDTO ingredienteReceitaDTO) {
        ingredienteReceitaValidator.validar(ingredienteReceitaDTO);
        Receita receita = buscarEntidadePorId(idReceita);
        Ingrediente ingrediente = ingredienteRepository.findById(ingredienteReceitaDTO.getIdIngrediente())
                .orElseThrow(() -> new RegraNegocioException("Ingrediente não encontrado com id: " + ingredienteReceitaDTO.getIdIngrediente()));

        Optional<IngredienteReceita> existingIngredienteReceita = ingredienteReceitaRepository
                .findByReceitaAndIngredienteAndDataFimIsNull(receita, ingrediente);

        if (existingIngredienteReceita.isPresent()) {
            throw new RegraNegocioException("Este ingrediente já foi adicionado a esta receita.");
        }

        IngredienteReceita ingredienteReceita = converterIngredienteReceita(ingredienteReceitaDTO);
        ingredienteReceita.setReceita(receita);
        ingredienteReceita.setIngrediente(ingrediente);
        ingredienteReceita.setDataFim(null);

        ingredienteReceita = ingredienteReceitaRepository.save(ingredienteReceita);
        return IngredienteReceitaDTO.create(ingredienteReceita);
    }

    public IngredienteReceitaDTO atualizarIngredienteNaReceita(Long idReceita, Long idIngredienteReceita, IngredienteReceitaDTO ingredienteReceitaDTO) {
        ingredienteReceitaValidator.validar(ingredienteReceitaDTO);
        Receita receita = buscarEntidadePorId(idReceita);

        IngredienteReceita ingredienteReceitaExistente = ingredienteReceitaRepository.findById(idIngredienteReceita)
                .orElseThrow(() -> new RegraNegocioException("Ingrediente da Receita não encontrado com id: " + idIngredienteReceita));

        if (ingredienteReceitaExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar um ingrediente da receita que já foi deletado com id: " + idIngredienteReceita);
        }

        if (!ingredienteReceitaExistente.getReceita().getId().equals(idReceita)) {
            throw new RegraNegocioException("O ingrediente da receita não pertence à receita informada.");
        }

        Ingrediente ingredienteNovo = ingredienteRepository.findById(ingredienteReceitaDTO.getIdIngrediente())
                .orElseThrow(() -> new RegraNegocioException("Novo ingrediente não encontrado com id: " + ingredienteReceitaDTO.getIdIngrediente()));

        ingredienteReceitaExistente.setQuantidade(ingredienteReceitaDTO.getQuantidade());
        ingredienteReceitaExistente.setObservacoes(ingredienteReceitaDTO.getObservacoes());
        ingredienteReceitaExistente.setUnidade(ingredienteReceitaDTO.getUnidade());
        ingredienteReceitaExistente.setIngrediente(ingredienteNovo);

        IngredienteReceita ingredienteReceitaAtualizado = ingredienteReceitaRepository.save(ingredienteReceitaExistente);
        return IngredienteReceitaDTO.create(ingredienteReceitaAtualizado);
    }

    public void removerIngredienteDaReceita(Long idReceita, Long idIngredienteReceita) {
        Receita receita = buscarEntidadePorId(idReceita);

        IngredienteReceita ingredienteReceita = ingredienteReceitaRepository.findById(idIngredienteReceita)
                .orElseThrow(() -> new RegraNegocioException("Ingrediente da Receita não encontrado com id: " + idIngredienteReceita));

        if (ingredienteReceita.getDataFim() != null) {
            throw new RegraNegocioException("Ingrediente da Receita com id: " + idIngredienteReceita + " já foi deletado.");
        }

        if (!ingredienteReceita.getReceita().getId().equals(idReceita)) {
            throw new RegraNegocioException("O ingrediente da receita não pertence à receita informada.");
        }

        ingredienteReceita.setDataFim(LocalDate.now());
        ingredienteReceitaRepository.save(ingredienteReceita);
    }
}
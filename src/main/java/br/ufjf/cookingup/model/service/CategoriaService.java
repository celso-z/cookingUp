package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.CategoriaDTO;
import br.ufjf.cookingup.model.entity.Categoria;
import br.ufjf.cookingup.model.repository.CategoriaRepository;
import br.ufjf.cookingup.model.validator.CategoriaValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaValidator validator;

    public Categoria converter(CategoriaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Categoria.class);
    }

    public void converterParaEntidade(CategoriaDTO dto, Categoria categoria) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, categoria);
    }

    public CategoriaDTO salvar(CategoriaDTO dto) {
        validator.validar(dto);
        Categoria categoria = converter(dto);
        categoria.setDataInicio(LocalDate.now());
        categoria.setDataFim(null);
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria);
    }

    public List<CategoriaDTO> buscarTodos() {
        return categoriaRepository.findByDataFimIsNull()
                .stream()
                .map(CategoriaDTO::new)
                .collect(Collectors.toList());
    }

    public Categoria buscarEntidadePorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada com id: " + id));
    }

    public CategoriaDTO buscarDTOporId(Long id) {
        Categoria categoria = buscarEntidadePorId(id);
        if (categoria.getDataFim() != null) {
            throw new RegraNegocioException("Categoria com id: " + id + " já foi deletada.");
        }
        return new CategoriaDTO(categoria);
    }

    public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
        validator.validar(dto);
        Categoria categoriaExistente = buscarEntidadePorId(id);

        if (categoriaExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar uma categoria que já foi deletada com id: " + id);
        }

        converterParaEntidade(dto, categoriaExistente);
        categoriaExistente.setId(id);

        Categoria categoriaAtualizada = categoriaRepository.save(categoriaExistente);
        return new CategoriaDTO(categoriaAtualizada);
    }

    public void deletar(Long id) {
        Categoria categoria = buscarEntidadePorId(id);

        if (categoria.getDataFim() != null) {
            throw new RegraNegocioException("Categoria com id: " + id + " já foi deletada.");
        }
        categoria.setDataFim(LocalDate.now());
        categoriaRepository.save(categoria);
    }
}
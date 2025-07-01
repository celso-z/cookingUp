package br.ufjf.cookingup.model.service;

import br.ufjf.cookingup.exception.RegraNegocioException;
import br.ufjf.cookingup.model.dto.UsuarioDTO;
import br.ufjf.cookingup.model.entity.Usuario;
import br.ufjf.cookingup.model.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private ModelMapper modelMapper;

    public UsuarioDTO salvar(UsuarioDTO dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setDataCadastro(LocalDate.now());
        usuario.setDataFim(null);
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    public List<UsuarioDTO> buscarTodos() {
        return usuarioRepository.findByDataFimIsNull()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    public Usuario buscarEntidadePorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado com id: " + id));
    }

    public UsuarioDTO buscarDTOporId(Long id) {
        Usuario usuario = buscarEntidadePorId(id);
        if (usuario.getDataFim() != null) {
            throw new RegraNegocioException("Usuário com id: " + id + " já foi deletado.");
        }
        return new UsuarioDTO(usuario);
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario usuarioExistente = buscarEntidadePorId(id);

        if (usuarioExistente.getDataFim() != null) {
            throw new RegraNegocioException("Não é possível atualizar um usuário que já foi deletado com id: " + id);
        }

        modelMapper.map(dto, usuarioExistente);
        usuarioExistente.setId(id);

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return new UsuarioDTO(usuarioAtualizado);
    }

    public void deletar(Long id) {
        Usuario usuario = buscarEntidadePorId(id);

        if (usuario.getDataFim() != null) {
            throw new RegraNegocioException("Usuário com id: " + id + " já foi deletado.");
        }

        usuario.setDataFim(LocalDate.now());
        usuarioRepository.save(usuario);
    }
}
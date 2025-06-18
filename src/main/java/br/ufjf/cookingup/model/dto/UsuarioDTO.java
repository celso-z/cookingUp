package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataCadastro;
    private String fotoPerfil;

    public UsuarioDTO(Usuario usuario) {
        if (usuario == null)
            return;
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.dataCadastro = usuario.getDataCadastro();
        this.fotoPerfil = usuario.getFotoPerfil();
    }
}

package br.ufjf.cookingup.model.validator;

import br.ufjf.cookingup.model.dto.AdministradorDTO;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class AdministradorValidator {

    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public void validar(AdministradorDTO administradorDTO) {
        if (administradorDTO.getNome() == null || administradorDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do administrador não pode ser vazio");
        }

        if (administradorDTO.getEmail() == null || administradorDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }

        if (!Pattern.matches(EMAIL_PATTERN, administradorDTO.getEmail())) {
            throw new IllegalArgumentException("Email deve ter formato válido");
        }

        if (administradorDTO.getSenha() == null || administradorDTO.getSenha().length() < 8) {
            throw new IllegalArgumentException("Senha do administrador deve ter pelo menos 8 caracteres");
        }
    }
}
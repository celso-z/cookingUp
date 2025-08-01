package br.ufjf.cookingup.model.validator;

import br.ufjf.cookingup.model.dto.MembroDTO;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class MembroValidator {

    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public void validar(MembroDTO membroDTO) {
        // Validações herdadas de UsuarioDTO
        if (membroDTO.getNome() == null || membroDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do membro não pode ser vazio");
        }

        if (membroDTO.getEmail() == null || membroDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }

        if (!Pattern.matches(EMAIL_PATTERN, membroDTO.getEmail())) {
            throw new IllegalArgumentException("Email deve ter formato válido");
        }

        if (membroDTO.getSenha() == null || membroDTO.getSenha().length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
        }

        // Validações específicas de MembroDTO
        if (membroDTO.getNivel() == null || membroDTO.getNivel() < 1 || membroDTO.getNivel() > 10) {
            throw new IllegalArgumentException("Nível do membro deve estar entre 1 e 10");
        }

        if (membroDTO.getNome().length() > 100) {
            throw new IllegalArgumentException("Nome não pode ter mais de 100 caracteres");
        }
    }
}
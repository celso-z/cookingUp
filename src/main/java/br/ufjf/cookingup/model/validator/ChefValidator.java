package br.ufjf.cookingup.model.validator;

import br.ufjf.cookingup.model.dto.ChefDTO;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class ChefValidator {

    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public void validar(ChefDTO chefDTO) {
        if (chefDTO.getNome() == null || chefDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do chef não pode ser vazio");
        }

        if (chefDTO.getEmail() == null || chefDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }

        if (!Pattern.matches(EMAIL_PATTERN, chefDTO.getEmail())) {
            throw new IllegalArgumentException("Email deve ter formato válido");
        }

        if (chefDTO.getSenha() == null || chefDTO.getSenha().length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
        }

        if (chefDTO.getBiografia() != null && chefDTO.getBiografia().length() > 1000) {
            throw new IllegalArgumentException("Biografia não pode ter mais de 1000 caracteres");
        }
    }
}
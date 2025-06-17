package br.ufjf.cookingup.model.dto;

import br.ufjf.cookingup.model.entity.Administrador;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdministradorDTO extends UsuarioDTO {

    public static AdministradorDTO create(Administrador administrador) {
        ModelMapper modelMapper = new ModelMapper();
        AdministradorDTO dto = modelMapper.map(administrador, AdministradorDTO.class);
        return dto;
    }
}

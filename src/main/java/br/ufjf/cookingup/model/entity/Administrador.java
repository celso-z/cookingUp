package br.ufjf.cookingup.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Administrador extends Usuario {

}

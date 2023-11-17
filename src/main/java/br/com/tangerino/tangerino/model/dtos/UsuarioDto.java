package br.com.tangerino.tangerino.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
}

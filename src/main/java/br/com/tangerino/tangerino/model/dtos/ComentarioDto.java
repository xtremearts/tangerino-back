package br.com.tangerino.tangerino.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ComentarioDto {
    private Long id;
    private PublicacaoDto publicacao;
    private UsuarioDto usuario;
    private String comentario;
    private Date dtCriacao;


}

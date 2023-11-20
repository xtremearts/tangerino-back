package br.com.tangerino.tangerino.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PublicacaoDto {
    private Long id;
    private UsuarioDto usuario;
    private String descricao;
    private Date dtCriacao;

}

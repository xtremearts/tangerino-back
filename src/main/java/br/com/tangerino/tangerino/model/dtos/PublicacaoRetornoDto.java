package br.com.tangerino.tangerino.model.dtos;

import br.com.tangerino.tangerino.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PublicacaoRetornoDto {

    private Long id;
    private UsuarioDto usuario;
    private String descricao;
    private Date dtCriacao;
    private byte[] imagem;

}
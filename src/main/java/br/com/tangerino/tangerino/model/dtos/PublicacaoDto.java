package br.com.tangerino.tangerino.model.dtos;

import br.com.tangerino.tangerino.model.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PublicacaoDto {
    private Long id;
    private Usuario usuario;
    private String descricao;
    private Date dtCriacao;

    public PublicacaoDto() {
        this.dtCriacao = new Date();
    }
}

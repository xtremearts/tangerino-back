package br.com.tangerino.tangerino.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AlbumDto {
    private Long id;
    private String extensaoArquivo;
    private Date dtCriacao;
    private UsuarioDto usuario;

}

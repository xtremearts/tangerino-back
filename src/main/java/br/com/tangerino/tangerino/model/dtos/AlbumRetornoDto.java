package br.com.tangerino.tangerino.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AlbumRetornoDto {
    private Long id;
    private String extensaoArquivo;
    private Date dtCriacao;
    private UsuarioDto usuario;
    private byte[] imagem;
}

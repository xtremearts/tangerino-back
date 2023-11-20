package br.com.tangerino.tangerino.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String extensaoArquivo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;

    @ManyToOne
    @JoinColumn
    private Usuario usuario;

    public Album() {
        this.dtCriacao = new Date();
    }
}

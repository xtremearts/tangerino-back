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
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Publicacao publicacao;

    @ManyToOne
    private Usuario usuario;

    private String comentario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;

    public Comentario() {
        this.dtCriacao = new Date();
    }
}

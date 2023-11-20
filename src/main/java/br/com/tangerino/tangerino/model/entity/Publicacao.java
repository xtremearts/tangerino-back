package br.com.tangerino.tangerino.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    @NotNull(message = "O usuário precisa está relacionado ao post")
    private Usuario usuario;

    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;

    private String extensaoArquivo;

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    public Publicacao() {
        this.dtCriacao = new Date();
    }
}

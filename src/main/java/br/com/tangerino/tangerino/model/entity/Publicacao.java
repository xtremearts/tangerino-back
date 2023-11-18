package br.com.tangerino.tangerino.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
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


}

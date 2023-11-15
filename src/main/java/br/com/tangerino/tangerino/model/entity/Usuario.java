package br.com.tangerino.tangerino.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, message = "O nome precisa ter no mínimo 3 caractéres.")
    private String nome;

    @NotNull
    @CPF(message = "O CPF digitado é inválido")
    private String cpf;

    @Email
    @NotEmpty(message = "Informe o email para login")
    @Size(message = "O email precisa ser válido")
    private String email;

    @NotEmpty(message = "Informe a senha")
    @Size(min = 5, message = "A senha precisa ter no mínimo 5 caractéres")
    private String password;


}

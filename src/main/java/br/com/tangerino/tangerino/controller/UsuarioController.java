package br.com.tangerino.tangerino.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/usuario")
public class UsuarioController{

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String obterPorId(@PathVariable("id") Long id) {
        return "O id selecionado é" + id.toString();
    }


}

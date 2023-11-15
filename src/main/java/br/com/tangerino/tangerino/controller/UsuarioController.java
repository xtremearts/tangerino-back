package br.com.tangerino.tangerino.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/usuario")
public class UsuarioController {

    @GetMapping("/{id}")
    public String obterPorId(@PathVariable("id") Long id) {
        return "O id selecionado é" + id.toString();

    }
}

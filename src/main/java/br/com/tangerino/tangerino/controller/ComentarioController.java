package br.com.tangerino.tangerino.controller;

import br.com.tangerino.tangerino.model.dtos.ComentarioDto;
import br.com.tangerino.tangerino.model.dtos.ComentarioFilterDto;
import br.com.tangerino.tangerino.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/comentario")
public class ComentarioController {
    private final ComentarioService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ComentarioDto> salvar(@RequestBody ComentarioFilterDto dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping(value = "obter-todos-por-usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ComentarioDto>> obterTodos() {
        return ResponseEntity.ok(service.obterComentariosUsuario());
    }


    @GetMapping(value = "obter-por-publicacao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ComentarioDto>> obterPorPublicacao(@PathVariable("id") Long idPublicacao) {
        return ResponseEntity.ok(service.obterComentariosPublicacoes(idPublicacao));
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}

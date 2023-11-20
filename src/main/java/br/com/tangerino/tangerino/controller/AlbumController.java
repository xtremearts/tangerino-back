package br.com.tangerino.tangerino.controller;

import br.com.tangerino.tangerino.model.dtos.AlbumDto;
import br.com.tangerino.tangerino.model.dtos.AlbumRetornoDto;
import br.com.tangerino.tangerino.model.dtos.PublicacaoRetornoDto;
import br.com.tangerino.tangerino.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/album")
public class AlbumController {
    private final AlbumService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<List<AlbumDto>> salvar(@RequestPart(value = "arquivos") List<MultipartFile> arquivos) {
        return ResponseEntity.ok(service.salvar(arquivos));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AlbumRetornoDto>> obterTodos() {
        return ResponseEntity.ok(service.obterTodos());
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}

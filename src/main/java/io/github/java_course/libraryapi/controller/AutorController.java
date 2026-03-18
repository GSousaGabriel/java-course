package io.github.java_course.libraryapi.controller;

import io.github.java_course.libraryapi.dto.AutorDTO;
import io.github.java_course.libraryapi.model.Autor;
import io.github.java_course.libraryapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor){
        var autorIdentidade = autor.mapearParaAutor();
        var newId = autorService.salvar(autorIdentidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        var autorBanco = autorService.obterPorId(idAutor);

        if(autorBanco.isPresent()){
            Autor autor = autorBanco.get();
            AutorDTO dto = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorBanco = autorService.obterPorId(idAutor);

        if(autorBanco.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        autorService.deletar(autorBanco.get());

        return ResponseEntity.noContent().build();
    }
}

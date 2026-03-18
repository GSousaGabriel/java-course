package io.github.java_course.libraryapi.service;

import io.github.java_course.libraryapi.model.Autor;
import io.github.java_course.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public UUID salvar(Autor autor) {
        return autorRepository.save(autor).getId();
    }

    public Optional<Autor> obterPorId(UUID id) {
        return autorRepository.findById(id);
    }

    public Void deletar(Autor autor) {
        autorRepository.delete(autor);
        return null;
    }
}

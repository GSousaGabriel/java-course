package io.github.java_course.libraryapi.repository;

import io.github.java_course.libraryapi.model.GeneroLivro;
import io.github.java_course.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {
    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        var livro = new Livro();
        livro.setIsbn("3123-312");
        livro.setTitulo("Titulo");
        livro.setDataPublicacao(LocalDate.of(1950, 1, 10));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setIdAutor(autorRepository.findById(UUID.fromString("a3abe8ed-2d38-468c-ac43-eee79a2fbb23")).orElse(null));

        var livroSalvo = livroRepository.save(livro);

        System.out.println(livroSalvo);
    }
}
package io.github.java_course.libraryapi.repository;

import io.github.java_course.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1990, 1, 1));

        var autorSalvo = autorRepository.save(autor);
        System.out.println(autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("c6a72390-22b0-4809-89e3-46eaf33dbc4b");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autor = possivelAutor.get();
            System.out.println("Encontrado: " + autor);

            autor.setDataNascimento(LocalDate.of(1960, 2, 25));

            autorRepository.save(autor);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> listaAutores = autorRepository.findAll();
        listaAutores.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println(autorRepository.count());
    }

    @Test
    public void deleteByIdTest(){
        var id = UUID.fromString("c6a72390-22b0-4809-89e3-46eaf33dbc4b");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autor = possivelAutor.get();
            System.out.println("Encontrado: " + autor);

            autorRepository.deleteById(autor.getId());
        }
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("89311b1d-e698-41c0-bd4e-6496bdddd942");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autor = possivelAutor.get();
            System.out.println("Encontrado: " + autor);

            autorRepository.delete(autor);
        }
    }
}

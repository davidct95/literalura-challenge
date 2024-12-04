package com.alura.literalura.principal;

import com.alura.literalura.dto.AuthorDTO;
import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;
import com.alura.literalura.model.DatosAPI;
import com.alura.literalura.service.AuthorService;
import com.alura.literalura.service.BookService;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    Scanner entrada = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();
    ConvierteDatos convierteDatos = new ConvierteDatos();
    final String URL_BASE = "https://gutendex.com/books/";
    BookService bookService;
    AuthorService authorService;

    public Principal(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public void mostrarMenu() {

        int opcion = -1;

        while (opcion != 0) {
            System.out.println("Selecciona una opción");
            System.out.println("1. Buscar Libro desde Gutendex");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en determinado año");
            System.out.println("5. Listar libros por idioma");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutorPorAnio();
                    break;
                case 5:
                    listarLibroPorIdioma();
                    break;
                case 0:
                    break;
            }
        }
    }

    public void buscarLibroPorTitulo() {

        System.out.println("Escriba el nombre del libro");
        String title = entrada.nextLine();
        String search = title.replace(" ", "%20");
        String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + search);
        DatosAPI datosAPI = convierteDatos.obtenerDatos(json, DatosAPI.class);

        Optional<BookDTO> bookOptional = datosAPI.results().stream()
                .findFirst()
                .map(b -> {
                    List<AuthorDTO> authorsDTO = b.authors().stream()
                            .map(a -> new AuthorDTO(a.name(), a.birthDay(), a.deathDay()))
                            .collect(Collectors.toList());
                    return new BookDTO(b.title(), b.languages(), b.copyright(), b.media_type(), b.download_count(), authorsDTO);
                });

        bookOptional.ifPresent(bookDTO -> {
            List<AuthorDTO> authorsDTO = bookDTO.authorsDTO();
            Book book = new Book(bookDTO.title(), bookDTO.languages(), bookDTO.copyright(), bookDTO.media_type(), bookDTO.download_count());
            Author author;

            for (AuthorDTO authorDTO : authorsDTO) {
                try {
                    author = authorService.findAuthorByName(authorDTO.name());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (author != null) {
                    author.addBook(book);
                    authorService.saveAuthor(author);
                } else {
                    Author newAuthor = new Author(authorDTO.name(), authorDTO.birthDay(), authorDTO.deathDay());
                    book.addAuthors(newAuthor);
                    bookService.saveBook(book);
                }
            }
        });
    }

    private void listarLibrosRegistrados() {

        this.bookService.findAllBooks()
                .forEach(System.out::println);

    }

    private void listarAutoresRegistrados() {

        this.authorService.findAllAuthors()
                .forEach(System.out::println);

    }

    private void listarAutorPorAnio() {

        System.out.println("Ingrese el año");
        int anio = entrada.nextInt();
        this.authorService.findByAuthorByAnio(anio)
                .forEach(System.out::println);

    }

    private void listarLibroPorIdioma() {

        System.out.println("""
                Ingrese el idioma para buscar los libros
                es - español
                en - inglés
                fr - francés 
                pt - portugués
                """);

        String idioma = entrada.nextLine();
        System.out.println(idioma);

        this.bookService.findByLanguages(idioma)
                .forEach(System.out::println);

    }

}




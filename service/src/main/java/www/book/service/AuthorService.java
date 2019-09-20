package www.book.service;

import www.book.api.Author;

import java.util.List;

public interface AuthorService {

    List<Author> listAll();

    Author get(long id);

    void add(Author author);

    void update(long id, Author author);

    void delete(long id);


}

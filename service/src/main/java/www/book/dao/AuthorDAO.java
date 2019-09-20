package www.book.dao;

import www.book.api.Author;

import java.util.List;

public interface AuthorDAO {

    List<Author> listAll();

    Author get(long id);

    void add(Author author);

    void update(Author author);

    void delete(long id);
}

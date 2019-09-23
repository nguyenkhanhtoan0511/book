package www.book.rest;

import www.book.api.Author;
import www.book.api.Book;
import www.book.service.AuthorService;
import www.book.service.BookService;
import www.book.vo.BookVO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class BookRestImpl implements BookRest{

    private BookService m_bookService;

    private AuthorService m_authorService;

    public BookRestImpl(BookService bookService, AuthorService authorService) {
        m_bookService = bookService;
        m_authorService = authorService;
    }

    @Override
    public Response listAll() {
        try{
            List<BookVO> bookVOs = new ArrayList<>();
            List<Book> books = m_bookService.listAll();
            for(Book book:books){
                bookVOs.add(new BookVO(book));
            }
            return Response.ok().entity(bookVOs).build();
        }catch(Exception e){
            return Response.serverError().entity("List book failed. " + e.getMessage()).build();
        }

    }

    @Override
    public Response get(long id) {
        try{
            Book book = m_bookService.get(id);
            return Response.ok().entity(new BookVO(book)).build();
        }catch(Exception e){
            return Response.serverError().entity("Get book failed. " + e.getMessage()).build();
        }
    }

    @Override
    public Response add(Book book) {
        try{
            m_bookService.add(book);
            return Response.ok().entity("Add book successfully").build();
        }catch(Exception e){
            return Response.serverError().entity("Add book failed. " + e.getMessage()).build();
        }
    }

    @Override
    public Response update(long id, Book book) {
        try{
            Book bookFromDb =  m_bookService.get(id);
            if(bookFromDb !=null) {
                bookFromDb.setTitle(book.getTitle());
                bookFromDb.setDescription(book.getDescription());
                bookFromDb.setPrice(book.getPrice());
                bookFromDb.setPublicationDate(book.getPublicationDate());
                bookFromDb.setActive(book.getActive());
            }
            m_bookService.update(bookFromDb);
            return Response.ok().entity("Update id " + id + " book successfully").build();
        }catch(Exception e){
            return Response.serverError().entity("Update book id " + id + " failed. " + e.getMessage()).build();
        }
    }

    @Override
    public Response delete(long id) {
        try{
            Book book = m_bookService.get(id);
            if(book == null){
                throw new Exception("Book with id " + id + " does not exist");
            }
            m_bookService.delete(id);
            return Response.ok().entity("Delete id " + id + " successfully").build();
        }catch(Exception e){
            return Response.serverError().entity("Delete book id " + id + " failed. " + e.getMessage()).build();
        }
    }

    @Override
    public Response listAllBookByAuthor(long authorId) {
        try{
            List<BookVO> bookVOs = new ArrayList<>();
            Author author = m_authorService.get(authorId);
            if(author == null) {
                throw new Exception("Author with id " + authorId + " does not exist");
            }
            List<Book> books = author.getBooks();
            for(Book book:books){
                bookVOs.add(new BookVO(book));
            }
            return Response.ok().entity(bookVOs).build();
        }catch(Exception e){
            return Response.serverError().entity("List book with author id " + authorId + " failed. " + e.getMessage()).build();
        }
    }

    @Override
    public Response addBookWithAuthor(long authorId, Book book) {
        try{
            Author author = m_authorService.get(authorId);
            if(author == null) {
                throw new Exception("Author with id " + authorId + " does not exist");
            }
            book.setAuthor(author);
            m_bookService.add(book);
            return Response.ok().entity("Add book with Author id " + authorId + " successfully").build();
        }catch(Exception e){
            return Response.serverError().entity("Add book with id " + authorId + " failed. " + e.getMessage()).build();
        }
    }

}

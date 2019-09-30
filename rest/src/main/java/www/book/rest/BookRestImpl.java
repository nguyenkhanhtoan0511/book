package www.book.rest;

import www.book.api.Author;
import www.book.api.Book;
import www.book.response.BookResponse;
import www.book.service.AuthorService;
import www.book.service.BookService;
import www.book.vo.BookVO;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            return printFailResponse("List book failed. " + e.getMessage());
        }

    }

    @Override
    public Response get(long id) {
        try{
            Book book = m_bookService.get(id);
            return Response.ok().entity(new BookVO(book)).build();
        }catch(Exception e){
            return printFailResponse("Get book failed. " + e.getMessage());
        }
    }

    @Override
    public Response add(Book book) {
        try{
            m_bookService.add(book);
            return printPassResponse("Add book successfully");
        }catch(Exception e){
            return printFailResponse("Add book failed. " + e.getMessage());
        }
    }

    @Override
    public Response update(long id, BookVO bookVO) {
        if (null == bookVO) {
            return printFailResponse("Invalid input");
        }
        try{
            Book bookFromDb =  m_bookService.get(id);
            if (null == bookFromDb) {
                throw new Exception("Book with id " + id + " does not exist");
            }
            bookFromDb.setTitle(bookVO.getTitle());
            bookFromDb.setDescription(bookVO.getDescription());
            bookFromDb.setPrice(bookVO.getPrice());
            bookFromDb.setPublicationDate(bookVO.getPublicationDate());
            bookFromDb.setActive(bookVO.isActive());

            String author_id = bookVO.getAuthor();
            long author_long = Long.parseLong(author_id);
            Author authorUpdate = m_authorService.get(author_long);

            bookFromDb.setAuthor(authorUpdate);

            m_bookService.update(bookFromDb);
            return printPassResponse("Update book successfully");
        }catch(Exception e){
            return printFailResponse("Update book failed. " + e.getMessage());
        }
    }

    @Override
    public Response delete(long id) {
        try{
            Book book = m_bookService.get(id);
            if(book == null){
                throw new Exception("Book with id " + id + " does not exist");
            }
            Author author = book.getAuthor();
            m_bookService.deleteWithAuthor(author, id);
            return printPassResponse("Delete book successfully");
        }catch(Exception e){
            return printFailResponse("Delete book id " + id + " failed. " + e.getMessage());
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
            return printFailResponse("List book with author id " + authorId + " failed. " + e.getMessage());
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
            return printPassResponse("Add book successfully");
        }catch(Exception e){
            return printFailResponse("Add book failed. " + e.getMessage());
        }
    }

    private Response printPassResponse(String message){
        BookResponse bookResponse = new BookResponse("Ok", message);
        return Response.ok().entity(bookResponse).build();
    }

    private Response printFailResponse(String message){
        BookResponse bookResponse = new BookResponse("Fail", message);
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(bookResponse)
                .build();
    }

}

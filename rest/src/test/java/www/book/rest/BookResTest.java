package www.book.rest;

import org.junit.Before;
import org.junit.Test;
import www.book.api.Author;
import www.book.api.Book;
import www.book.response.BookResponse;
import www.book.service.AuthorService;
import www.book.service.BookService;
import www.book.vo.BookVO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookResTest {

    private BookService m_bookService;

    private AuthorService m_authorService;

    private BookRestImpl m_bookRest;

    @Before
    public void init(){
        m_authorService = mock(AuthorService.class);
        m_bookService = mock(BookService.class);
        m_bookRest = new BookRestImpl(m_bookService, m_authorService);
    }

    @Test
    public void testListAllBook(){
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        book.setTitle("Best Wish");
        books.add(book);
        when(m_bookService.listAll()).thenReturn(books);
        Response actual =  m_bookRest.listAll();
        List<BookVO> bookVOs = (List<BookVO>) actual.getEntity();
        assertEquals(200, actual.getStatus());
        assertEquals(1, bookVOs.size());
        assertEquals("Best Wish", bookVOs.get(0).getTitle());
    }

    @Test
    public void testGetBook(){
        Book book = new Book();
        book.setTitle("Best Wish");
        long id = 1;
        when(m_bookService.get(id)).thenReturn(book);
        Response actual = m_bookRest.get(id);
        BookVO bookVO = (BookVO) actual.getEntity();
        assertEquals(200, actual.getStatus());
        assertEquals("Best Wish", bookVO.getTitle());
    }

    @Test
    public void testAddBook_WithAuthorNull(){
        long authorId = 1;
        Book book = new Book();
        when(m_authorService.get(authorId)).thenReturn(null);
        Response actual = m_bookRest.addBookWithAuthor(authorId, book);
        BookResponse bookResponse = (BookResponse) actual.getEntity();
        assertEquals(400, actual.getStatus());
        assertEquals("Fail", bookResponse.getStatus());
        assertEquals("Add book failed. Author with id " + authorId + " does not exist", bookResponse.getMessage());
    }


    @Test
    public void testAddBook(){
        long authorId = 1;
        Book book = new Book();
        Author author = new Author();
        when(m_authorService.get(authorId)).thenReturn(author);
        Response actual = m_bookRest.addBookWithAuthor(authorId, book);
        BookResponse bookResponse = (BookResponse) actual.getEntity();
        assertEquals(200, actual.getStatus());
        assertEquals("Ok", bookResponse.getStatus());
        assertEquals("Add book successfully", bookResponse.getMessage());

    }

    @Test
    public void testUpdateBook_WithInvalidInput(){
        long id = 1;
        BookVO bookVO = null;
        Response actual = m_bookRest.update(id, bookVO);
        BookResponse bookResponse = (BookResponse) actual.getEntity();
        assertEquals(400, actual.getStatus());
        assertEquals("Fail", bookResponse.getStatus());
        assertEquals("Invalid input", bookResponse.getMessage());
    }

    @Test
    public void testUpdateBook(){
        long id = 1;
        long authorId = 1;
        Date date = new Date();
        BookVO bookV0 = new BookVO();
        bookV0.setAuthor(authorId);
        Author authorFromDB = new Author();
        Book bookFromDB = new Book("Best Wish", "HCM", date, 1122);
        when(m_bookService.get(id)).thenReturn(bookFromDB);
        when(m_authorService.get(authorId)).thenReturn(authorFromDB);
        Response actual = m_bookRest.update(id , bookV0);
        BookResponse bookResponse = (BookResponse) actual.getEntity();
        assertEquals(200, actual.getStatus());
        assertEquals("Update book successfully", bookResponse.getMessage());

    }

}

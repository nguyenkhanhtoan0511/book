package www.book.rest;

import org.junit.Before;
import org.junit.Test;
import www.book.api.Author;
import www.book.api.Book;
import www.book.response.BookResponse;
import www.book.service.AuthorService;
import www.book.vo.AuthorVO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthorRestTest {

    private AuthorRestImpl m_authorRest;
    private AuthorService m_authorService;

    @Before
    public void init(){
        m_authorService = mock(AuthorService.class);
        m_authorRest = new AuthorRestImpl(m_authorService);
    }

    @Test
    public void testGetListAuthor() {
        List<Author> authors = new ArrayList<>() ;
        Author author = new Author();
        author.setFirstName("Toan");
        author.setLastName("Nguyen");
        authors.add(author);
        when(m_authorService.listAll()).thenReturn(authors);
        Response actual = m_authorRest.listAll();
        assertEquals(200,actual.getStatus());
        List<AuthorVO> authorVOList = (List<AuthorVO>) actual.getEntity();
        assertEquals(1, authorVOList.size());
        assertEquals("Toan", authorVOList.get(0).getFirstName());
        assertEquals("Nguyen", authorVOList.get(0).getLastName());
    }

    @Test
    public void testGetAuthor(){
        Author author = new Author();
        author.setId(1);
        author.setFirstName("Keith");
        author.setLastName("Ferrazzi");
        when(m_authorService.get(1)).thenReturn(author);
        Response actual = m_authorRest.get(1);
        assertEquals(200, actual.getStatus());
        AuthorVO authorVO = (AuthorVO) actual.getEntity();
        assertEquals("Keith", authorVO.getFirstName());
        assertEquals("Ferrazzi", authorVO.getLastName());
    }


    @Test
    public void testAddAuthor(){
        Author author =  new Author();
        Response actual = m_authorRest.add(author);
        BookResponse bookResponse = (BookResponse) actual.getEntity();
        assertEquals(200, actual.getStatus());
        assertEquals("Add author successfully", bookResponse.getMessage());
        assertEquals("Ok", bookResponse.getStatus());
    }

    @Test
    public void testUpdateInputAuthorNull(){
        Response actual = m_authorRest.update(1, null);
        assertEquals(500, actual.getStatus());
        assertEquals("Invalid input", actual.getEntity());
    }

    @Test
    public void testUpdateAuthorFromDBNull(){
        long id = 1;
        Author author = new Author();
        when(m_authorService.get(1)).thenReturn(null);
        Response actual = m_authorRest.update(1, author);
        BookResponse bookResponse = (BookResponse) actual.getEntity();
        assertEquals(400, actual.getStatus());
        assertEquals("Fail", bookResponse.getStatus());
        assertEquals("Update book author id " + id +" failed. " + "Author with id " + id + " does not exist", bookResponse.getMessage());
    }

    @Test
    public void testUpdate(){
        Date date = new Date();
        Author authorFromDB = new Author("Robin", "Sharma", date, null, null);
        when(m_authorService.get(1)).thenReturn(authorFromDB);
        Author author = new Author("Camilo", "Cruz", date, "HCM City", null);
        Response actual = m_authorRest.update(1, author);
        assertEquals(200, actual.getStatus());
        BookResponse bookResponse = (BookResponse) actual.getEntity();
        assertEquals("Ok", bookResponse.getStatus());
        assertEquals("Update author successfully", bookResponse.getMessage());
    }


    @Test
    public void testDeleteAuthorNull(){
        long id = 1;
        when(m_authorService.get(id)).thenReturn(null);
        Response actual = m_authorRest.delete(id);
        BookResponse bookResponse = (BookResponse) actual.getEntity();
        assertEquals("Fail", bookResponse.getStatus());
        assertEquals("Delete author id " + id + " failed. Author with id " + id + " does not exist", bookResponse.getMessage());
    }

    @Test
    public void testDeleteAuthorSetBook(){
        Book book = new Book();
        List<Book> books = new ArrayList<>();
        books.add(book);
        Author author = new Author();
        author.setId(1);
        author.setBooks(books);
        long id = 1;
        when(m_authorService.get(id)).thenReturn(author);
        Response actual = m_authorRest.delete(id);
        BookResponse bookResponse = (BookResponse) actual.getEntity();
        assertEquals(400, actual.getStatus());
        assertEquals("Fail", bookResponse.getStatus());
        assertEquals("Delete author id 1 failed. The author id " + id + " can't delete. Author is still being used by some books.", bookResponse.getMessage());
    }

    @Test
    public void testDeleteAuthor(){
        Author author = new Author();
        author.setId(1);
        long id = 1;
        when(m_authorService.get(id)).thenReturn(author);
        Response actual = m_authorRest.delete(id);
        BookResponse bookResponse = (BookResponse) actual.getEntity();
        assertEquals("Ok", bookResponse.getStatus());
        assertEquals("Delete author successfully", bookResponse.getMessage());
    }




}

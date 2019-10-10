package www.book.rest;

import org.junit.Before;
import org.junit.Test;
import www.book.api.Author;
import www.book.service.AuthorService;
import www.book.vo.AuthorVO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
        authors.add(author);
        when(m_authorService.listAll()).thenReturn(authors);

        Response actual = m_authorRest.listAll();

        assertEquals(200,actual.getStatus());

        List<AuthorVO> authorVOList = (List<AuthorVO>) actual.getEntity();
        assertEquals(1, authorVOList.size());
        assertEquals("Toan", authorVOList.get(0).getFirstName());

    }

//    @Test
//    public void testGetListAuthor_WithException() {
//
//        doThrow(new Exception()).when(m_authorService.listAll());
//
//        Response actual = m_authorRest.listAll();
//
//        assertEquals(400, actual.getStatus());
//
//
//    }
}

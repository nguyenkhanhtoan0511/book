package www.book.rest;

import www.book.api.Author;
import www.book.service.AuthorService;
import www.book.vo.AuthorVO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class AuthorRestImpl implements AuthorRest {

    private AuthorService m_authorService;

    public AuthorRestImpl(AuthorService authorService) {
        m_authorService = authorService;
    }

    @Override
    public Response listAll() {
        try{
            List<AuthorVO> authorVOs = new ArrayList<>();
            List<Author> authors = m_authorService.listAll();
            for (Author author : authors) {
                authorVOs.add(new AuthorVO(author));
            }
            return Response.ok().entity(authorVOs).build();
        }catch(Exception e){
            return Response.serverError().entity("Listing author failed. " + e.getMessage()).build();
        }
    }

    @Override
    public Response get(long id) {
        try{
            Author author = m_authorService.get(id);
            return Response.ok().entity(new AuthorVO(author)).build();
        }catch(Exception e){
            return Response.serverError().entity("Get author id " + id + " failed. " + e.getMessage()).build();
        }
    }

    @Override
    public Response add(Author author) {
        try{
            m_authorService.add(author);
            return Response.ok().entity("Add author successfully").build();
        }catch(Exception e){
            return Response.serverError().entity("Add author failed. " + e.getMessage()).build();
        }
    }

    @Override
    public Response update(long id, Author author) {
        if(null == author){
            return Response.serverError().entity("Invalid input").build();
        }

        try{
            Author authorFromDb = m_authorService.get(id);
            if(authorFromDb == null){
                throw new Exception("Author with id " + id + " does not exist");
            }
            authorFromDb.setFirstName(author.getFirstName());
            authorFromDb.setLastName(author.getLastName());
            authorFromDb.setDescription(author.getDescription());
            authorFromDb.setAddress(author.getAddress());
            authorFromDb.setDob(author.getDob());
            authorFromDb.setActive(author.getActive());
            m_authorService.update(id, authorFromDb);
            return Response.ok().entity("Update author id " + id + " successfully").build();
        }catch(Exception e){
            return Response.serverError().entity("Update book author id " + id +" failed. " + e.getMessage()).build();
        }
    }

    @Override
    public Response delete(long id) {
        try{
            Author authorFromDb = m_authorService.get(id);
            if(authorFromDb == null){
                throw new Exception("Author with id " + id + " does not exist");
            }
            if(!authorFromDb.getBooks().isEmpty()){
                throw new Exception("The author id " + id + " can't delete. Author is still being used by some books.");
            }
            m_authorService.delete(id);
            return Response.ok().entity("Delete author id " + id + " successfully").build();
        }catch(Exception e){
            return Response.serverError().entity("Delete author id " + id + " failed. " + e.getMessage()).build();
        }

    }

}

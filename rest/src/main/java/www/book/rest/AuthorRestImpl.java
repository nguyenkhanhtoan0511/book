package www.book.rest;

import www.book.api.Author;
import www.book.response.BookResponse;
import www.book.service.AuthorService;
import www.book.vo.AuthorVO;

import javax.ws.rs.core.MediaType;
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
            return printFailResponse("Listing author failed. " + e.getMessage());
        }
    }

    @Override
    public Response get(long id) {
        try{
            Author author = m_authorService.get(id);
            return Response.ok().entity(new AuthorVO(author)).build();
        }catch(Exception e){
            return printFailResponse("Get author id " + id + " failed. " + e.getMessage());
        }
    }

    @Override
    public Response add(Author author) {
        try{
            m_authorService.add(author);
            return printPassResponse("Add author successfully");
        }catch(Exception e){
            return printFailResponse("Add author failed. " + e.getMessage());
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
            return printPassResponse("Update author id " + id + " successfully");
        }catch(Exception e){
            return printFailResponse("Update book author id " + id +" failed. " + e.getMessage());
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
            return printPassResponse("Delete author id " + id + " successfully");
        }catch(Exception e){
            return printFailResponse("Delete author id " + id + " failed. " + e.getMessage());
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

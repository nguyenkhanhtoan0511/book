package www.book.rest;

import www.book.api.Book;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/book")
public interface BookRest {

    @Path("/")
    @Produces("application/json")
    @GET
    List<Book> listAll();

    @Path("/{id}")
    @Produces("application/json")
    @GET
    Book get(@PathParam("id") long id);

    @Path("/")
    @Consumes("application/json")
    @POST
    void add(Book book);

    @Path("/{id}")
    @Consumes("application/json")
    @PUT
    void update(@PathParam("id") long id, Book book);

    @Path("/{id}")
    @DELETE
    void delete(@PathParam("id") long id);

    @Path("/author/{id}")
    @Produces("application/json")
    @GET
    List<Book> listAllBookByAuthor(@PathParam("id") long authorId);

    @Path("/author/{id}")
    @Produces("application/json")
    @POST
    void addBookWithAuthor(@PathParam("id") long authorId, Book book);

}

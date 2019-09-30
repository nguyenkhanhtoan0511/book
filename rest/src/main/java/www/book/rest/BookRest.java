package www.book.rest;

import www.book.api.Book;
import www.book.vo.BookVO;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/book")
public interface BookRest {

    @Path("/")
    @Produces("application/json")
    @GET
    Response listAll();

    @Path("/{id}")
    @Produces("application/json")
    @GET
    Response get(@PathParam("id") long id);

    @Path("/")
    @Consumes("application/json")
    @POST
    Response add(Book book);

    @Path("/{id}")
    @Consumes("application/json")
    @PUT
    Response update(@PathParam("id") long id, BookVO bookVO);

    @Path("/{id}")
    @DELETE
    Response delete(@PathParam("id") long id);

    @Path("/author/{id}")
    @Produces("application/json")
    @GET
    Response listAllBookByAuthor(@PathParam("id") long authorId);

    @Path("/author/{id}")
    @Produces("application/json")
    @POST
    Response addBookWithAuthor(@PathParam("id") long authorId, Book book);

}

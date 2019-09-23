package www.book.rest;

import www.book.api.Author;
import www.book.vo.AuthorVO;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/author")
public interface AuthorRest {

    @Path("/")
    @Produces("application/json")
    @GET
    Response listAll();

    @Path("/{id}")
    @Produces("application/json")
    @GET
    Response get(@PathParam("id") long id);

    @Path("/")
    @Produces("application/json")
    @POST
    Response add(Author author);

    @Path("/{id}")
    @Produces("application/json")
    @PUT
    Response update(@PathParam("id") long id, Author author);

    @Path("/{id}")
    @DELETE
    Response delete(@PathParam("id") long id);

}

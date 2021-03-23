package net.wilsn.tournament;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

public abstract class AbstractResource<T extends PanacheEntity> {
    @Inject
    PanacheRepository<T> repository;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("sort") List<String> sortQuery,
                         @QueryParam("page") @DefaultValue("0") int pageIndex,
                         @QueryParam("size") @DefaultValue("20") int pageSize) {
        Page page = Page.of(pageIndex, pageSize);
        Sort sort = Utils.getSortFromQuery(sortQuery);
        List<T> tournaments = repository.findAll(sort).page(page).list();
        long resourceCount = repository.count();
        return Response.ok(tournaments)
                .header("X-Total-Count", resourceCount)
                .build();
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(T resourceToSave) {
        resourceToSave.persistAndFlush();
        URI createdUri = uriInfo.getAbsolutePathBuilder().path(Long.toString(resourceToSave.id)).build();
        return Response.created(createdUri).entity(resourceToSave).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        if (!repository.deleteById(id)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}

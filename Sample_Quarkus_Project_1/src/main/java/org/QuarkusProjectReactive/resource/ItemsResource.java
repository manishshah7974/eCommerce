package org.QuarkusProjectReactive.resource;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.QuarkusProjectReactive.model.Items;
import org.QuarkusProjectReactive.repository.ItemsRepository;
import io.smallrye.mutiny.Uni;

import java.util.List;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemsResource {

    @Inject
    ItemsRepository itemsRepository;

    // Endpoint to fetch all items
    @GET
    public Uni<List<Items>> getAllItems() {
        return itemsRepository.findAllItems();
    }

    // Endpoint to fetch an item by ID
    @GET
    @Path("/{itemId}")
    public Uni<Response> getItemById(@PathParam("itemId") Long itemId) {
        return itemsRepository.findItemById(itemId)
                .onItem().ifNotNull().transform(item -> Response.ok(item).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    // Endpoint to create a new item
    @POST
    public Uni<Response> createItem(Items item) {
        return itemsRepository.saveItem(item)
                .onItem().transform(savedItem -> Response.status(Response.Status.CREATED).entity(savedItem).build());
    }

    // Endpoint to update an existing item by ID
    @PUT
    @Path("/{itemId}")
    public Uni<Response> updateItem(@PathParam("itemId") Long itemId, Items item) {
        return itemsRepository.updateItem(itemId, item)
                .onItem().transform(updatedItem -> Response.ok(updatedItem).build())
                .onFailure().recoverWithItem(ex -> Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build());
    }

    // Endpoint to delete an item by ID
    @DELETE
    @Path("/{itemId}")
    public Uni<Response> deleteItem(@PathParam("itemId") Long itemId) {
        return itemsRepository.deleteItem(itemId)
                .onItem().transform(voidItem -> Response.noContent().build())
                .onFailure().recoverWithItem(ex -> Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build());
    }
}

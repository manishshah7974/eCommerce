package org.QuarkusProjectReactive.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.QuarkusProjectReactive.model.ItemDetails;
import org.QuarkusProjectReactive.repository.ItemDetailsRepository;
import io.smallrye.mutiny.Uni;

import java.util.List;

@Path("/item-details")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemDetailsResource {

    @Inject
    ItemDetailsRepository itemDetailsRepository;

    // Endpoint to fetch all item details
    @GET
    public Uni<List<ItemDetails>> getAllItemDetails() {
        return itemDetailsRepository.findAllItemDetails();
    }

    // Endpoint to fetch item details by ID
    @GET
    @Path("/{itemId}")
    public Uni<Response> getItemDetailsById(@PathParam("itemId") Long itemId) {
        return itemDetailsRepository.findItemDetailsById(itemId)
                .onItem().ifNotNull().transform(itemDetails -> Response.ok(itemDetails).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    // Endpoint to create new item details
    @POST
    public Uni<Response> createItemDetails(ItemDetails itemDetails) {
        return itemDetailsRepository.saveItemDetails(itemDetails)
                .onItem().transform(savedItemDetails -> Response.status(Response.Status.CREATED).entity(savedItemDetails).build());
    }

    // Endpoint to update item details by ID
    @PUT
    @Path("/{itemId}")
    public Uni<Response> updateItemDetails(@PathParam("itemId") Long itemId, ItemDetails itemDetails) {
        return itemDetailsRepository.updateItemDetails(itemId, itemDetails)
                .onItem().transform(updatedItemDetails -> Response.ok(updatedItemDetails).build())
                .onFailure().recoverWithItem(ex -> Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build());
    }

    // Endpoint to delete item details by ID
    @DELETE
    @Path("/{itemId}")
    public Uni<Response> deleteItemDetails(@PathParam("itemId") Long itemId) {
        return itemDetailsRepository.deleteItemDetails(itemId)
                .onItem().transform(voidItem -> Response.noContent().build())
                .onFailure().recoverWithItem(ex -> Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build());
    }
}

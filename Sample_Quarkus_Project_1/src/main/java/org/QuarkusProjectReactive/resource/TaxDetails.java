package org.QuarkusProjectReactive.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.QuarkusProjectReactive.model.ItemDetails;
import org.QuarkusProjectReactive.repository.ItemDetailsRepository;
import org.QuarkusProjectReactive.service.CalculateTaxes;

import java.util.List;

@Path("/tax")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaxDetails {

    @Inject
    private CalculateTaxes calculateTaxes;

    @Inject
    private ItemDetailsRepository itemDetailsRepository;


    @GET
    @Path("/order/{id}")
    public Uni<List<ItemDetails>> getOrderById(@PathParam("id") String id) {
        // Start calculating taxes asynchronously
        return calculateTaxes.calculateTaxforOrder(id)
                .onItem().invoke(() -> System.out.println("Tax calculation completed for order " + id))
                .replaceWith(itemDetailsRepository.findItemDetailsByOrderId(id));  // Return the updated item details
    }

}

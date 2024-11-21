package org.QuarkusProjectReactive.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.QuarkusProjectReactive.model.Orders;
import org.QuarkusProjectReactive.repository.OrdersRepository;
import io.smallrye.mutiny.Uni;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrdersRepository ordersRepository;

    // Endpoint to fetch all orders
    @GET
    public Uni<List<Orders>> getAllOrders() {
        return ordersRepository.findAllOrders();
    }

    // Endpoint to fetch an order by ID
    @GET
    @Path("/{id}")
    public Uni<Response> getOrderById(@PathParam("id") Long id) {
        return ordersRepository.findOrderById(id)
                .onItem().ifNotNull().transform(order -> Response.ok(order).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    // Endpoint to create a new order
    @POST
    public Uni<Response> createOrder(Orders order) {
        return ordersRepository.saveOrders(order)
                .onItem().transform(savedOrder -> Response.status(Response.Status.CREATED).entity(savedOrder).build());
    }

    // Endpoint to update an existing order by ID
    @PUT
    @Path("/{id}")
    public Uni<Response> updateOrder(@PathParam("id") Long id, Orders order) {
        return ordersRepository.updateOrder(id, order)
                .onItem().transform(updatedOrder -> Response.ok(updatedOrder).build())
                .onFailure().recoverWithItem(ex -> Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build());
    }

    // Endpoint to delete an order by ID
    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteOrder(@PathParam("id") Long id) {
        return ordersRepository.deleteOrder(id)
                .onItem().transform(voidItem -> Response.noContent().build())
                .onFailure().recoverWithItem(ex -> Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build());
    }
}

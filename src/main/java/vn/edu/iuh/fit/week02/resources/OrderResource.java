package vn.edu.iuh.fit.week02.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.Order;
import vn.edu.iuh.fit.week02.services.OrderService;
import vn.edu.iuh.fit.week02.services.impl.OrderServiceImpl;

import java.util.List;
import java.util.Optional;

@Path("/orders")
public class OrderResource {
    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public OrderResource() {
        this.orderService = new OrderServiceImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Order> orders = orderService.findAll();
        return Response.ok(orders).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Optional<Order> order = orderService.findById(id);
        return order
                .map(o -> Response.ok(order))
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Order order) {
        Optional<Order> existingOrder = orderService.findById(order.getOrderId());
        return existingOrder
                .map(o -> Response.status(Response.Status.FOUND))
                .orElseGet(() -> {
                    orderService.save(order);
                    return Response.ok(order);
                })
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Order order) {
        Optional<Order> updatedOrder = orderService.findById(order.getOrderId());
        return updatedOrder
                .map(o -> {
                    orderService.update(order);
                    return Response.ok(order);
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            orderService.delete(id);
            return Response.ok(order).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

package vn.edu.iuh.fit.week02.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.Order;
import vn.edu.iuh.fit.week02.services.OrderService;
import vn.edu.iuh.fit.week02.services.impl.OrderServiceImpl;

import java.sql.Date;
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

    @GET
    @Path("/statistics/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByDate(@PathParam("date") String dateString) {
        try {
            Date date = Date.valueOf(dateString);
            List<Order> orders = orderService.findByOrderDate(date);
            return Response.ok(orders).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid date format. Use yyyy-MM-dd").build();
        }
    }

    @GET
    @Path("/statistics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByDateRange(
            @QueryParam("startDate") String startDateString,
            @QueryParam("endDate") String endDateString
    ) {
        try {
            Date startDate = Date.valueOf(startDateString);
            Date endDate = Date.valueOf(endDateString);
            List<Order> orders = orderService.findByOrderDateRange(startDate, endDate);
            return Response.ok(orders).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid date format. Use yyyy-MM-dd").build();
        }
    }

    @GET
    @Path("/statistics/salesperson/{empId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderStatisticsBySalespersonAndDateRange(
            @PathParam("empId") Long empId,
            @QueryParam("startDate") Date startDate,
            @QueryParam("endDate") Date endDate) {
        List<Order> orders = orderService.findBySalespersonAndDateRange(empId, startDate, endDate);
        return Response.ok(orders).build();
    }

}

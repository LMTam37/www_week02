package vn.edu.iuh.fit.week02.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.OrderDetail;
import vn.edu.iuh.fit.week02.services.OrderDetailService;
import vn.edu.iuh.fit.week02.services.impl.OrderDetailServiceImpl;

import java.util.Optional;

@Path("/order-details")
public class OrderDetailResource {
    private final OrderDetailService orderDetailService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public OrderDetailResource() {
        this.orderDetailService = new OrderDetailServiceImpl();
    }

    @GET
    @Path("/{orderId}/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("orderId") Long orderId, @PathParam("productId") Long productId) {
        Optional<OrderDetail> orderDetail = orderDetailService.findById(orderId, productId);
        return orderDetail
                .map(od -> Response.ok(od))
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(OrderDetail orderDetail) {
        Optional<OrderDetail> existingOrderDetail = orderDetailService.findById(orderDetail.getOrder().getOrderId(), orderDetail.getProduct().getProductId());
        return existingOrderDetail
                .map(od -> Response.status(Response.Status.FOUND))
                .orElseGet(() -> {
                    orderDetailService.save(orderDetail);
                    return Response.ok(orderDetail);
                })
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(OrderDetail orderDetail) {
        Optional<OrderDetail> updateOrderDetail = orderDetailService.findById(orderDetail.getOrder().getOrderId(), orderDetail.getProduct().getProductId());
        return updateOrderDetail
                .map(od -> {
                    orderDetailService.update(orderDetail);
                    return Response.ok(orderDetail);
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("/{productId}/{imageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("productId") Long productId,
                           @PathParam("imageId") Long imageId) {
        Optional<OrderDetail> orderDetail = orderDetailService.findById(productId, imageId);
        return orderDetail
                .map(od -> {
                    orderDetailService.delete(productId, imageId);
                    return Response.ok(orderDetail);
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}
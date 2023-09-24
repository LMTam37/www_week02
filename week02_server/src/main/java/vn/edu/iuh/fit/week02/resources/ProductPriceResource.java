package vn.edu.iuh.fit.week02.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.week02.models.ProductPrice;
import vn.edu.iuh.fit.week02.services.ProductPriceService;
import vn.edu.iuh.fit.week02.services.impl.ProductPriceServiceImpl;

import java.sql.Date;
import java.util.Optional;

@Path("/product-prices")
public class ProductPriceResource {
    private final ProductPriceService productPriceService;

    public ProductPriceResource() {
        this.productPriceService = new ProductPriceServiceImpl();
    }

    @GET
    @Path("/{productId}/{priceDateTime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("productId") Long productId, @PathParam("priceDateTime") Date priceDateTime) {
        Optional<ProductPrice> productPrice = productPriceService.findById(productId, priceDateTime);
        return productPrice
                .map(pp -> Response.ok(pp))
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ProductPrice productPrice) {
        Optional<ProductPrice> existingProductPrice = productPriceService.findById(productPrice.getProduct().getProductId(), productPrice.getPriceDateTime());
        return existingProductPrice
                .map(pp -> Response.status(Response.Status.FOUND))
                .orElseGet(() -> {
                    productPriceService.save(productPrice);
                    return Response.ok(productPrice);
                })
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ProductPrice productPrice) {
        Optional<ProductPrice> updatedProductPrice = productPriceService.findById(productPrice.getProduct().getProductId(), productPrice.getPriceDateTime());
        return updatedProductPrice
                .map(pp -> Response.status(Response.Status.NOT_FOUND))
                .orElseGet(() -> {
                    productPriceService.update(productPrice);
                    return Response.ok(productPrice);
                })
                .build();
    }

    @DELETE
    @Path("/{productId}/{priceDateTime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("productId") Long productId,
                           @PathParam("priceDateTime") Date priceDateTime) {
        Optional<ProductPrice> productPrice = productPriceService.findById(productId, priceDateTime);
        if (productPrice.isPresent()) {
            productPriceService.delete(productId, priceDateTime);
            return Response.ok(productPrice.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

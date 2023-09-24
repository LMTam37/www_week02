package vn.edu.iuh.fit.week02.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.ProductImage;
import vn.edu.iuh.fit.week02.services.ProductImageService;
import vn.edu.iuh.fit.week02.services.impl.ProductImageServiceImpl;

import java.util.Optional;

@Path("/product-images")
public class ProductImageResource {
    private final ProductImageService productImageService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductImageResource() {
        this.productImageService = new ProductImageServiceImpl();
    }

    @GET
    @Path("/{productId}/{imageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("productId") Long productId, @PathParam("imageId") Long imageId) {
        Optional<ProductImage> productImage = productImageService.findById(productId, imageId);
        return productImage
                .map(pi -> Response.ok(pi))
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ProductImage productImage) {
        Optional<ProductImage> existingProductImage = productImageService.findById(productImage.getProduct().getProductId(), productImage.getImageId());
        return existingProductImage
                .map(pi -> Response.status(Response.Status.FOUND))
                .orElseGet(() -> {
                    productImageService.save(productImage);
                    return Response.ok(productImage);
                })
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ProductImage productImage) {
        Optional<ProductImage> updateProductImage = productImageService.findById(productImage.getProduct().getProductId(), productImage.getImageId());
        return updateProductImage
                .map(pi -> {
                    productImageService.update(productImage);
                    return Response.ok(productImage);
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("/{productId}/{imageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("productId") Long productId,
                           @PathParam("imageId") Long imageId) {
        Optional<ProductImage> productImage = productImageService.findById(productId, imageId);
        return productImage
                .map(pi -> {
                    productImageService.delete(productId, imageId);
                    return Response.ok(productImage);
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}

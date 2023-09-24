package vn.edu.iuh.fit.week02.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.Product;
import vn.edu.iuh.fit.week02.services.ProductService;
import vn.edu.iuh.fit.week02.services.impl.ProductServiceImpl;

import java.util.List;
import java.util.Optional;

@Path("/products")
public class ProductResource {
    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductResource(){
        this.productService = new ProductServiceImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Product> products = productService.findAll();
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Optional<Product> product = productService.findById(id);
        return product
                .map(p -> Response.ok(p))
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Product product) {
        Optional<Product> existingProduct = productService.findById(product.getProductId());
        return existingProduct
                .map(p -> Response.status(Response.Status.FOUND))
                .orElseGet(() -> {
                    productService.save(product);
                    return Response.ok(product);
                })
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Product product) {
        Optional<Product> updatedProduct = productService.findById(product.getProductId());
        return updatedProduct
                .map(p -> {
                    productService.update(product);
                    return Response.ok(product);
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Optional<Product> product = productService.findById(id);
        return product
                .map(p -> {
                    productService.delete(id);
                    return Response.ok(product);
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}

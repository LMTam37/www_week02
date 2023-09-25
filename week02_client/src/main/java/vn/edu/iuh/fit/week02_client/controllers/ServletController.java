package vn.edu.iuh.fit.week02_client.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/controls")
public class ServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "products":
                handleProductsRequest(req, resp);
                break;
            case "cart":
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
                break;
            case "checkout":
                req.getRequestDispatcher("checkout.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    private void handleProductsRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/week02_war/api/products"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String, Object>> products = objectMapper.readValue(responseBody, new TypeReference<>() {
                });
                req.getSession().setAttribute("products", products);
                req.getRequestDispatcher("products.jsp").forward(req, resp);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "addToCart":
                try {
                    Long productId = Long.parseLong(req.getParameter("productId"));

                    if (productId > 0) {
                        List<Long> cartItems = (List<Long>) req.getSession().getAttribute("cartItems");
                        if (cartItems == null) {
                            cartItems = new ArrayList<>();
                        }
                        cartItems.add(productId);
                        req.getSession().setAttribute("cartItems", cartItems);
                        req.getRequestDispatcher("products.jsp").forward(req, resp);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}

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
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
            case "checkout":
                try {
                    List<Map<String, Object>> products = (List<Map<String, Object>>) req.getSession().getAttribute("products");
                    Long productId = Long.parseLong(req.getParameter("productId"));
                    Map<String, Object> product = products.get(productId.intValue()-1);
                    int quantity = Integer.parseInt(req.getParameter("quantity"));
                    Date orderDate = new Date(System.currentTimeMillis());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = dateFormat.format(orderDate);

                    Map<String, Object> orderMap = new HashMap<>();
                    orderMap.put("orderDate", formattedDate);
                    orderMap.put("employee", null);

                    List<Map<String, Object>> orderDetails = new ArrayList<>();
                    Map<String, Object> orderDetail = new HashMap<>();
                    orderDetail.put("product", product);
                    orderDetail.put("quantity", quantity);
                    orderDetail.put("productPrice", null);
                    orderDetails.add(orderDetail);

                    orderMap.put("orderDetails",orderDetails);

                    ObjectMapper objectMapper = new ObjectMapper();
                    String orderJson = objectMapper.writeValueAsString(orderMap);

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI("http://localhost:8080/week02_war/api/orders"))
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(orderJson))
                            .build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    if(response.statusCode() == 200){
                        System.out.println("Order created");
                    }else{
                        System.out.println("order = " + orderJson);
                        System.out.println(response.toString());
                        System.out.println("fail");
                    }
                } catch (Exception e) {
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

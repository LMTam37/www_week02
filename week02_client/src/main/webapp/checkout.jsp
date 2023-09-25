<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Checkout</h1>
    <a href="index.jsp" class="btn btn-secondary mb-3">Back</a>

    <table class="table mt-4">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>Image</th>
            <th>Description</th>
            <th>Unit</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Map<String, Object>> products = (List<Map<String, Object>>) request.getSession().getAttribute("products");
            List<Long> cartItems = (List<Long>) request.getSession().getAttribute("cartItems");
            if (cartItems != null) {
                for (Long productId : cartItems) {
                    Map<String, Object> product = products.get(productId.intValue());
        %>
        <tr>
            <td><%= product.get("name") %>
            </td>
            <td><img src="<%= product.get("imageUrl") %>" alt="<%= product.get("name") %>" width="100" height="100">
            </td>
            <td><%= product.get("description") %>
            </td>
            <td><%= product.get("unit") %>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

    <button class="btn btn-primary" onclick="checkout()">Checkout</button>
</div>

<script>
    function checkout() {
        alert("Thank you for your purchase!");
    }
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Products</h1>
    <table class="table mt-4">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>Image</th>
            <th>Description</th>
            <th>Unit</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody><%
            List<Map<String, Object>> products = (List<Map<String, Object>>) request.getAttribute("products");

            for (Map<String, Object> product : products) {
        %>
        <tr>
            <td>
                <%= product.get("name") %>
            </td>
            <td>
                <img
                        src="<%= product.get("imageUrl") %>"
                        alt="<%= product.get("name") %>"
                        width="100" height="100">
            </td>
            <td>
                <%= product.get("description") %>
            </td>
            <td>
                <%= product.get("unit") %>
            </td>
            <td>
                <form action="controls?action=addToCart" method="post">
                    <input type="hidden" name="productId" value="<%= product.get("id") %>">
                    <button type="submit" class="btn btn-primary">Add to Cart</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>

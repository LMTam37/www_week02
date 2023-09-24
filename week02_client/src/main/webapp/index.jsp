<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<h1>Welcome to the Online Store</h1>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <button class="btn btn-primary" onclick="sendRequest('products')">Hiển thị Sản Phẩm</button>
        </div>
        <div class="col-md-4">
            <button class="btn btn-success" onclick="sendRequest('cart')">Giỏ Hàng</button>
        </div>
        <div class="col-md-4">
            <button class="btn btn-danger" onclick="sendRequest('checkout')">Thanh Toán</button>
        </div>
    </div>
</div>

<script>
    function sendRequest(action) {
        window.location.href = 'controls?action=' + action;
    }
</script>

</body>
</html>

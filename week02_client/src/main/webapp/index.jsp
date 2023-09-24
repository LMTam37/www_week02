<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<button class="btn btn-primary" onclick="sendRequest('products')">Hiển thị Sản Phẩm</button>
<button class="btn btn-primary" onclick="sendRequest('cart')">Giỏ Hàng</button>
<button class="btn btn-primary" onclick="sendRequest('checkout')">Thanh Toán</button>

<script>
    function sendRequest(action) {
        window.location.href = 'controls?action=' + action;
    }
</script>

</body>
</html>

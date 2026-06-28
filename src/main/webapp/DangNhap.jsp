<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="flex justify-center items-center min-h-screen bg-gray-50">

<form action="DangNhapController" method="post"
      class="bg-white p-6 rounded shadow w-96">

    <h2 class="text-xl font-bold mb-4 text-center">Đăng nhập</h2>

    <input name="tenUser" placeholder="Username"
           class="border w-full p-2 mb-3 rounded">

    <input name="matKhau" type="password"
           placeholder="Password"
           class="border w-full p-2 mb-3 rounded">

    <button class="bg-green-600 text-white w-full py-2 rounded">
        Login
    </button>

</form>

</body>
</html>
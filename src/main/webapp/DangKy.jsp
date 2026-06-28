<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Đăng ký tài khoản</title>

    <script src="https://cdn.tailwindcss.com"></script>

</head>

<body class="bg-gray-50 flex justify-center items-center min-h-screen p-4 font-sans">

<div class="bg-white p-8 rounded-lg shadow-sm border border-gray-100 w-full max-w-md my-8">

    <h2 class="text-2xl font-bold mb-8 text-center">

        Đăng ký tài khoản

    </h2>

    <% String error = (String) request.getAttribute("error"); %>

    <% if(error != null){ %>

        <div class="mb-4 p-3 rounded bg-red-100 text-red-700">

            <%= error %>

        </div>

    <% } %>

    <% String success = (String) request.getAttribute("success"); %>

    <% if(success != null){ %>

        <div class="mb-4 p-3 rounded bg-green-100 text-green-700">

            <%= success %>

        </div>

    <% } %>

    <form action="${pageContext.request.contextPath}/DangKyController"
          method="post">

        <div class="mb-4">

            <label class="block mb-1">

                Họ và tên

            </label>

            <input
                    type="text"
                    name="tenKhachHang"
                    required
                    value="${param.tenKhachHang}"
                    class="w-full border rounded p-2"
                    placeholder="Nhập họ tên">

        </div>

        <div class="mb-4">

            <label class="block mb-1">

                CCCD

            </label>

            <input
                    type="text"
                    name="soCCCD"
                    required
                    value="${param.soCCCD}"
                    class="w-full border rounded p-2"
                    placeholder="Nhập số CCCD">

        </div>

        <div class="mb-4">

            <label class="block mb-1">

                Số điện thoại

            </label>

            <input
                    type="text"
                    name="soDienThoai"
                    required
                    value="${param.soDienThoai}"
                    class="w-full border rounded p-2"
                    placeholder="Nhập số điện thoại">

        </div>

        <div class="mb-4">

            <label class="block mb-1">

                Email

            </label>

            <input
                    type="email"
                    name="email"
                    required
                    value="${param.email}"
                    class="w-full border rounded p-2"
                    placeholder="Nhập email">

        </div>

        <div class="mb-4">

            <label class="block mb-1">

                Username

            </label>

            <input
                    type="text"
                    name="tenUser"
                    required
                    value="${param.tenUser}"
                    class="w-full border rounded p-2"
                    placeholder="Tên đăng nhập">

        </div>

        <div class="mb-8">

            <label class="block mb-1">

                Password

            </label>

            <input
                    type="password"
                    name="matKhau"
                    required
                    class="w-full border rounded p-2"
                    placeholder="Mật khẩu">

        </div>

        <button
                type="submit"
                class="w-full bg-blue-600 text-white py-2.5 rounded hover:bg-blue-700">

            Xác nhận đăng ký

        </button>

    </form>

    <div class="mt-6 text-center text-sm">

        Đã có tài khoản?

        <a href="${pageContext.request.contextPath}/DangNhap.jsp"
           class="text-blue-600 hover:underline font-medium">

            Đăng nhập

        </a>

    </div>

</div>

</body>

</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông tin tài khoản</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50 font-sans pb-20">

<header class="bg-white p-4 shadow-sm border-b flex items-center mb-6 sticky top-0">

    <a href="TrangChuController"
       class="border px-3 py-1.5 rounded hover:bg-gray-50">
        ⬅
    </a>

    <h1 class="text-lg md:text-xl font-bold flex-1 text-center pr-10">
        Thông tin tài khoản
    </h1>

</header>

<main class="max-w-3xl mx-auto p-4">

    <!-- CHƯA LOGIN -->
    <c:if test="${empty sessionScope.user}">
        <div class="bg-white p-6 rounded-lg shadow text-center text-gray-600">
            Bạn chưa đăng nhập.
            <div class="mt-4">
                <a href="DangNhap.jsp"
                   class="bg-blue-600 text-white px-4 py-2 rounded">
                    Đăng nhập ngay
                </a>
            </div>
        </div>
    </c:if>

    <!-- ĐÃ LOGIN -->
    <c:if test="${not empty sessionScope.user}">

        <div class="bg-white border rounded-lg overflow-hidden shadow-sm">

            <div class="flex flex-col">

                <!-- Họ tên -->
                <div class="flex border-b">
                    <div class="w-1/3 p-4 bg-gray-50 border-r font-medium text-center flex items-center justify-center">
                        Họ và tên
                    </div>
                    <div class="w-2/3 p-4">
                        ${profile.hoTen}
                    </div>
                </div>

                <!-- SĐT -->
                <div class="flex border-b">
                    <div class="w-1/3 p-4 bg-gray-50 border-r font-medium text-center flex items-center justify-center">
                        Số điện thoại
                    </div>
                    <div class="w-2/3 p-4">
                        ${profile.soDienThoai}
                    </div>
                </div>

                <!-- Email -->
                <div class="flex border-b">
                    <div class="w-1/3 p-4 bg-gray-50 border-r font-medium text-center flex items-center justify-center">
                        Email
                    </div>
                    <div class="w-2/3 p-4">
                        ${profile.email}
                    </div>
                </div>

                <!-- Username -->
                <div class="flex border-b">
                    <div class="w-1/3 p-4 bg-blue-50 border-r font-medium text-center flex items-center justify-center">
                        Username
                    </div>
                    <div class="w-2/3 p-4 text-gray-500">
                        ${profile.username}
                    </div>
                </div>

                <!-- Password -->
                <div class="flex">
                    <div class="w-1/3 p-4 bg-blue-50 border-r font-medium text-center flex items-center justify-center">
                        Mật khẩu
                    </div>
                    <div class="w-2/3 p-4 text-gray-500">
                        ••••••••
                    </div>
                </div>

            </div>

        </div>

    </c:if>

</main>

<!-- NAV -->
<nav class="fixed bottom-0 w-full bg-white border-t flex justify-around items-center p-2 text-xs md:text-sm shadow-lg">

    <a href="TrangChuController" class="flex flex-col items-center">
        <span class="text-xl">🏠</span><span>Trang chủ</span>
    </a>

    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <a href="DangNhap.jsp" class="flex flex-col items-center">
                <span class="text-xl">🔑</span><span>Đăng nhập</span>
            </a>
        </c:when>
        <c:otherwise>
            <a href="DangXuatController" class="flex flex-col items-center">
                <span class="text-xl">🚪</span><span>Đăng xuất</span>
            </a>
        </c:otherwise>
    </c:choose>

    <a href="LichSuDatPhongController" class="flex flex-col items-center">
        <span class="text-xl">📋</span><span>Lịch sử</span>
    </a>

    <a href="ProfileController" class="flex flex-col items-center text-blue-600 font-bold">
        <span class="text-xl">👤</span><span>Profile</span>
    </a>

    <a href="Chat.jsp" class="flex flex-col items-center">
        <span class="text-xl">🤖</span><span>Chat Bot</span>
    </a>

</nav>

</body>
</html>
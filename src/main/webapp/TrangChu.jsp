<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ - Đặt phòng</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50 pb-20 font-sans">

<!-- ================= HEADER ================= -->
<header class="bg-white shadow-sm border-b p-4 sticky top-0 z-10">

    <div class="flex justify-between items-center mb-4">
        <h1 class="text-xl font-bold">Đặt phòng khách sạn</h1>

  
    </div>

    <!-- SEARCH -->
    <form action="${pageContext.request.contextPath}/TrangChuController" method="get">
        <input type="text"
               name="tenKhachSan"
               value="${param.tenKhachSan}"
               placeholder="Tìm kiếm tên khách sạn..."
               class="w-full border rounded p-2 focus:outline-none focus:border-blue-500">
    </form>

</header>

<!-- ================= LIST HOTEL ================= -->
<main class="max-w-4xl mx-auto mt-6 p-4 grid grid-cols-1 md:grid-cols-2 gap-6">

    <c:choose>

        <c:when test="${not empty listKhachSan}">

            <c:forEach var="ks" items="${listKhachSan}">

                <div class="bg-white border rounded-lg shadow-sm overflow-hidden">

                    <img src="${pageContext.request.contextPath}/images/${ks.anh}"
                         class="w-full h-48 object-cover"
                         alt="${ks.tenKhachSan}">

                    <div class="p-5">

                        <h3 class="font-bold text-lg text-blue-900">
                            ${ks.tenKhachSan}
                        </h3>

                        <p class="text-gray-600">
                            ${ks.diaChi}, ${ks.thanhPho}
                        </p>

                        <div class="mt-3 text-right">

                            <a href="${pageContext.request.contextPath}/TraCuuPhongController?maKhachSan=${ks.maKhachSan}"
                               class="bg-black text-white px-4 py-2 rounded">
                                Xem phòng
                            </a>

                        </div>

                    </div>

                </div>

            </c:forEach>

        </c:when>

        <c:otherwise>
            <div class="col-span-2 text-center text-gray-500">
                Không tìm thấy khách sạn
            </div>
        </c:otherwise>

    </c:choose>

</main>

<!-- ================= BOTTOM NAV ================= -->
<nav class="fixed bottom-0 w-full bg-white border-t flex justify-around p-3 text-sm">

    <a href="${pageContext.request.contextPath}/TrangChuController">
        🏠 Trang chủ
    </a>

    <!-- LOGIN / LOGOUT -->
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/DangNhap.jsp">
                🔑 Đăng nhập
            </a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/DangXuatController">
                🚪 Đăng xuất
            </a>
        </c:otherwise>
    </c:choose>

    <!-- LỊCH SỬ -->
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/DangNhap.jsp">
                📋 Lịch sử
            </a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/LichSuDatPhongController">
                📋 Lịch sử
            </a>
        </c:otherwise>
    </c:choose>

    <!-- PROFILE -->
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/DangNhap.jsp">
                👤 Profile
            </a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/ProfileController">
                👤 Profile
            </a>
        </c:otherwise>
    </c:choose>

    <!-- CHATBOT (KHÔNG MẤT) -->
    <a href="${pageContext.request.contextPath}/Chat.jsp">
        🤖 Chat Bot
    </a>

</nav>

</body>
</html>
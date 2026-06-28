<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lịch sử đặt phòng</title>

    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50 font-sans pb-20">

<!-- HEADER -->
<header class="bg-white p-4 shadow-sm border-b flex items-center sticky top-0">

    <a href="${pageContext.request.contextPath}/TrangChuController"
       class="border px-3 py-1.5 rounded hover:bg-gray-100">
        ⬅
    </a>

    <h1 class="text-xl font-bold flex-1 text-center pr-10">
        Lịch sử đặt phòng
    </h1>

</header>

<!-- MAIN -->
<main class="max-w-5xl mx-auto p-4">

    <!-- CHƯA ĐĂNG NHẬP -->
    <c:if test="${empty sessionScope.user}">
        <div class="bg-white rounded-lg shadow p-10 text-center text-gray-600">
            Bạn chưa đăng nhập để xem lịch sử.

            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/DangNhap.jsp"
                   class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
                    Đăng nhập ngay
                </a>
            </div>
        </div>
    </c:if>

    <!-- ĐÃ ĐĂNG NHẬP -->
    <c:if test="${not empty sessionScope.user}">

        <c:choose>

            <c:when test="${not empty listDatPhong}">

                <c:forEach var="dp" items="${listDatPhong}">

                    <div class="bg-white border rounded-lg shadow-sm mb-5">

                        <div class="border rounded m-2 text-center font-bold py-2 bg-gray-50">
                            ${dp.tenKhachSan}
                        </div>

                        <div class="grid grid-cols-2 gap-2 p-3 text-sm text-center">

                            <div class="border p-2 rounded bg-gray-50">
                                Phòng ${dp.soPhong}
                            </div>

                            <div class="border p-2 rounded bg-gray-50">
                                ${dp.tenLoaiPhong}
                            </div>

                            <div class="border p-2 rounded">
                                Giá
                            </div>

                            <div class="border p-2 rounded font-bold text-red-600">
                                ${dp.giaDatPhong} VNĐ
                            </div>

                            <div class="border p-2 rounded">
                                Ngày đến
                            </div>

                            <div class="border p-2 rounded">
                                ${dp.ngayDen}
                            </div>

                            <div class="border p-2 rounded">
                                Ngày đi
                            </div>

                            <div class="border p-2 rounded">
                                ${dp.ngayDi}
                            </div>

                            <div class="border p-2 rounded">
                                Trạng thái
                            </div>

                            <div class="border p-2 rounded
                                ${dp.trangThai == 'Đã đặt' ? 'text-green-600' : 'text-red-600'}">
                                ${dp.trangThai}
                            </div>

                        </div>

                        <!-- HỦY -->
                        <c:if test="${dp.trangThai == 'Đã đặt'}">

                            <div class="p-3">

                                <form action="${pageContext.request.contextPath}/HuyDatPhongController"
                                      method="post">

                                    <input type="hidden"
                                           name="maPhieuDatPhong"
                                           value="${dp.maPhieuDatPhong}">

                                    <button onclick="return confirm('Bạn có chắc muốn hủy?')"
                                            class="w-full border border-red-500 text-red-500 py-2 rounded hover:bg-red-50">

                                        Hủy đặt phòng

                                    </button>

                                </form>

                            </div>

                        </c:if>

                    </div>

                </c:forEach>

            </c:when>

            <c:otherwise>
                <div class="bg-white rounded-lg shadow p-10 text-center text-gray-500">
                    Bạn chưa có lịch sử đặt phòng.
                </div>
            </c:otherwise>

        </c:choose>

    </c:if>

</main>

<!-- NAV -->
<nav class="fixed bottom-0 w-full bg-white border-t flex justify-around items-center p-2 shadow-lg">

    <a href="${pageContext.request.contextPath}/TrangChuController">
        🏠 Trang chủ
    </a>

    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/DangNhap.jsp">🔑 Đăng nhập</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/DangXuatController">🚪 Đăng xuất</a>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/LichSuDatPhongController"
       class="text-blue-600 font-bold">
        📋 Lịch sử
    </a>

    <a href="${pageContext.request.contextPath}/ProfileController">
        👤 Profile
    </a>

    <a href="${pageContext.request.contextPath}/Chat.jsp">
        🤖 Chat Bot
    </a>

</nav>

</body>
</html>
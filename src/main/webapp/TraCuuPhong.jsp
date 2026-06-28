<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách phòng</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 flex flex-col min-h-screen pb-20">

<header class="bg-white p-4 shadow-sm border-b flex items-center">
    <a href="TrangChuController" class="border px-3 py-1.5 rounded">⬅</a>
    <h1 class="text-xl font-bold flex-1 text-center">${khachSan.tenKhachSan}</h1>
</header>

<main class="max-w-4xl mx-auto w-full p-4">

<c:if test="${param.error == 'BOOK_FAILED'}">
    <div class="bg-red-100 text-red-700 p-3 rounded mb-4">
        ❌ Đặt phòng thất bại. Phòng đã được đặt trong khoảng thời gian này.
    </div>
</c:if>
<c:if test="${param.error == 'missing_date'}">
    <div class="bg-yellow-100 text-yellow-700 p-3 rounded mb-4">
        ⚠ Vui lòng chọn ngày đến và ngày đi
    </div>
</c:if>
<c:if test="${param.error == 'invalid_date'}">
    <div class="bg-yellow-100 text-yellow-700 p-3 rounded mb-4">
        ⚠ Ngày không hợp lệ, vui lòng chọn lại
    </div>
</c:if>

<c:set var="ngayDen" value="${param.ngayDen}" />
<c:set var="ngayDi"  value="${param.ngayDi}"  />

<form action="TraCuuPhongController" method="get"
      class="bg-white p-4 rounded shadow mb-4">

    <input type="hidden" name="maKhachSan" value="${khachSan.maKhachSan}">

    <label class="block mb-1 font-medium">Ngày đến</label>
    <input type="date" id="ngayDen" name="ngayDen"
           value="${ngayDen}"
           required
           class="border p-2 w-full mb-3 rounded">

    <label class="block mb-1 font-medium">Ngày đi</label>
    <input type="date" id="ngayDi" name="ngayDi"
           value="${ngayDi}"
           required
           class="border p-2 w-full mb-3 rounded">

    <label class="block mb-1 font-medium">Loại phòng</label>
    <select name="maLoaiPhong"
            class="border p-2 w-full mb-3 rounded"
            onchange="this.form.submit()">
        <option value="">Tất cả</option>
        <c:forEach var="lp" items="${listLoaiPhong}">
            <option value="${lp.maLoaiPhong}"
                <c:if test="${param.maLoaiPhong == lp.maLoaiPhong}">selected</c:if>>
                ${lp.tenLoaiPhong}
            </option>
        </c:forEach>
    </select>

    <button type="submit" class="bg-blue-600 text-white w-full py-2 rounded">
        Tìm phòng
    </button>
</form>

<c:forEach var="p" items="${listPhong}">
    <div class="bg-white p-4 border rounded mb-4 flex justify-between items-start">

        <div>
            <b>Phòng ${p.soPhong}</b><br>
            ${p.tenLoaiPhong}<br>
            Trạng thái:
            <span class="${p.tinhTrangPhong == 'Trống' ? 'text-green-600' : 'text-red-500'}">
                ${p.tinhTrangPhong}
            </span>
        </div>

        <div class="text-right">
            <b>${p.giaPhong} VNĐ/đêm</b><br><br>

            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <a href="DangNhap.jsp" class="text-blue-600 underline">Đăng nhập để đặt</a>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${p.tinhTrangPhong == 'Trống'}">
                            <!--
                                ✅ FIX CHÍNH: dùng JS để build link đặt phòng động.
                                Không dùng hasDate từ server nữa vì param chỉ có
                                sau khi submit form, còn ngày user đang chọn trong
                                input thì JS đọc được ngay.
                            -->
                            <span id="dat-phong-${p.maPhong}">
                                <a href="#"
                                   data-ma-phong="${p.maPhong}"
                                   data-ma-khach-san="${khachSan.maKhachSan}"
                                   onclick="handleDatPhong(this); return false;"
                                   class="bg-green-500 text-white px-3 py-1 rounded inline-block">
                                    Đặt ngay
                                </a>
                            </span>
                        </c:when>
                        <c:otherwise>
                            <span class="text-gray-400 text-sm">Đã được đặt</span>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</c:forEach>

<c:if test="${empty listPhong}">
    <div class="text-center text-gray-500 mt-10">Không có phòng phù hợp</div>
</c:if>

</main>

<script>
function handleDatPhong(el) {
    var ngayDen = document.getElementById('ngayDen').value;
    var ngayDi  = document.getElementById('ngayDi').value;

    if (!ngayDen || !ngayDi) {
        alert('Vui lòng chọn ngày đến và ngày đi trước khi đặt phòng!');
        return;
    }

    if (ngayDi <= ngayDen) {
        alert('Ngày đi phải sau ngày đến!');
        return;
    }

    var maPhong     = el.getAttribute('data-ma-phong');
    var maKhachSan  = el.getAttribute('data-ma-khach-san');

    var url = 'DatPhongController'
        + '?maPhong='    + maPhong
        + '&ngayDen='   + ngayDen
        + '&ngayDi='    + ngayDi
        + '&maKhachSan=' + maKhachSan;

    window.location.href = url;
}
</script>

</body>
</html>
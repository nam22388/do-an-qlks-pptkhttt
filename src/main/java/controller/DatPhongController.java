package controller;

import service.DatPhongService;
import model.PhieuDatPhong;
import model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

@WebServlet("/DatPhongController")
public class DatPhongController extends HttpServlet {

    private final DatPhongService service = new DatPhongService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            // ================= LẤY PARAM =================
            String maPhongStr  = request.getParameter("maPhong");
            String ngayDenStr  = request.getParameter("ngayDen");
            String ngayDiStr   = request.getParameter("ngayDi");
            String maKhachSan  = request.getParameter("maKhachSan");

            // ================= VALIDATE NULL =================
            if (maPhongStr == null || ngayDenStr == null || ngayDiStr == null
                    || maPhongStr.isBlank() || ngayDenStr.isBlank() || ngayDiStr.isBlank()) {

                response.sendRedirect("TraCuuPhongController?error=missing_date");
                return;
            }

            // ================= CHECK LOGIN =================
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("DangNhap.jsp");
                return;
            }

            User user = (User) session.getAttribute("user");

            // ================= PARSE =================
            int maPhong;
            try {
                maPhong = Integer.parseInt(maPhongStr);
            } catch (NumberFormatException e) {
                response.sendRedirect("TraCuuPhongController?error=invalid_room");
                return;
            }

            // ✅ Validate định dạng ngày trước khi parse
            Date ngayDen, ngayDi;
            try {
                ngayDen = Date.valueOf(ngayDenStr);
                ngayDi  = Date.valueOf(ngayDiStr);
            } catch (IllegalArgumentException e) {
                response.sendRedirect("TraCuuPhongController?error=invalid_date");
                return;
            }

            // ================= BUILD OBJECT =================
            PhieuDatPhong dp = new PhieuDatPhong();
            dp.setMaPhong(maPhong);
            dp.setMaKhachHang(user.getMaKhachHang());
            dp.setNgayDatPhong(new Date(System.currentTimeMillis()));
            dp.setNgayDen(ngayDen);
            dp.setNgayDi(ngayDi);

            // ================= CALL SERVICE =================
            boolean ok = service.datPhong(dp);

            // ================= SUCCESS =================
            if (ok) {
                response.sendRedirect("LichSuDatPhongController");
                return;
            }

            // ================= FAIL =================
            String enc = StandardCharsets.UTF_8.name();
            StringBuilder redirect = new StringBuilder("TraCuuPhongController?error=BOOK_FAILED")
                .append("&ngayDen=").append(URLEncoder.encode(ngayDenStr, enc))
                .append("&ngayDi=").append(URLEncoder.encode(ngayDiStr, enc));

            if (maKhachSan != null && !maKhachSan.isBlank()) {
                redirect.append("&maKhachSan=").append(URLEncoder.encode(maKhachSan, enc));
            }

            response.sendRedirect(redirect.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("TrangChuController?error=exception");
        }
    }
}
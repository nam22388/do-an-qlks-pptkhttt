package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.KhachHang;
import model.User;
import service.DangKyService;

import java.io.IOException;

@WebServlet("/DangKyController")
public class DangKyController extends HttpServlet {

    private final DangKyService service = new DangKyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // =========================
        // 1. Lấy dữ liệu từ JSP
        // =========================
        String tenKhachHang = request.getParameter("tenKhachHang");
        String soCCCD = request.getParameter("soCCCD");
        String soDienThoai = request.getParameter("soDienThoai");
        String email = request.getParameter("email");

        String tenUser = request.getParameter("tenUser");
        String matKhau = request.getParameter("matKhau");

        // =========================
        // 2. Map sang Model
        // =========================
        KhachHang kh = new KhachHang();
        kh.setTenKhachHang(tenKhachHang);
        kh.setSoCCCD(soCCCD);
        kh.setSoDienThoai(soDienThoai);
        kh.setEmail(email);

        User user = new User();
        user.setTenUser(tenUser);
        user.setMatKhau(matKhau);

        // =========================
        // 3. Gọi Service
        // =========================
        String result = service.dangKy(kh, user);

        // =========================
        // 4. Trả kết quả về JSP
        // =========================
        if (result.equals("Đăng ký thành công")) {
            request.setAttribute("success", result);
        } else {
            request.setAttribute("error", result);
        }

        request.getRequestDispatcher("/DangKy.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // mở form đăng ký
        request.getRequestDispatcher("/DangKy.jsp").forward(request, response);
    }
}
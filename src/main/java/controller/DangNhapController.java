package controller;

import model.User;
import service.DangNhapService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/DangNhapController")
public class DangNhapController extends HttpServlet {

    private final DangNhapService service = new DangNhapService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, jakarta.servlet.ServletException {

        String tenUser = request.getParameter("tenUser");
        String matKhau = request.getParameter("matKhau");

        User user = service.dangNhap(tenUser, matKhau);

        if (user != null) {

            HttpSession session = request.getSession();

            // 🔥 QUAN TRỌNG NHẤT
            session.setAttribute("user", user);
            session.setAttribute("maKhachHang", user.getMaKhachHang());

            response.sendRedirect("TrangChuController");

        } else {

            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
            request.getRequestDispatcher("DangNhap.jsp")
                   .forward(request, response);
        }
    }
}
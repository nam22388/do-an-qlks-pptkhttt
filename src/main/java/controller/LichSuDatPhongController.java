package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.User;
import service.DatPhongService;

import java.io.IOException;

@WebServlet("/LichSuDatPhongController")
public class LichSuDatPhongController extends HttpServlet {

    private final DatPhongService service = new DatPhongService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        // ❌ CHƯA LOGIN → redirect
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/DangNhap.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        int maKhachHang = user.getMaKhachHang();

        // lấy data
        var list = service.getLichSuByKhachHang(maKhachHang);

        // luôn set list (tránh null JSP crash)
        request.setAttribute("listDatPhong", list);

        request.getRequestDispatcher("/LichSuDatPhong.jsp")
               .forward(request, response);
    }
}
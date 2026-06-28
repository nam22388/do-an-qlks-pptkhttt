package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.UserProfileView;
import service.ThongTinHeThongService;

import java.io.IOException;

@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {

    private final ThongTinHeThongService service = new ThongTinHeThongService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, jakarta.servlet.ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/DangNhap.jsp");
            return;
        }

        model.User user = (model.User) session.getAttribute("user");

        int maKhachHang = user.getMaKhachHang();

        UserProfileView profile = service.getProfile(maKhachHang);

        request.setAttribute("profile", profile);

        request.getRequestDispatcher("/Profile.jsp")
                .forward(request, response);
    }
}
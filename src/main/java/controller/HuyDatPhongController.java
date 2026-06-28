package controller;

import service.DatPhongService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/HuyDatPhongController")
public class HuyDatPhongController extends HttpServlet {

    private final DatPhongService service = new DatPhongService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String maStr = request.getParameter("maPhieuDatPhong");

            if (maStr != null && !maStr.isEmpty()) {

                int maPhieu = Integer.parseInt(maStr);

                service.huyDatPhong(maPhieu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/LichSuDatPhongController");
    }

    // OPTIONAL: tránh 405 nếu ai gọi GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/LichSuDatPhongController");
    }
}
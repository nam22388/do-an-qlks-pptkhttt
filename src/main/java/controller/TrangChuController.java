package controller;

import service.ThongTinHeThongService;
import model.KhachSan;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/TrangChuController")
public class TrangChuController extends HttpServlet {

    private final ThongTinHeThongService service = new ThongTinHeThongService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // =========================
        // 1. LẤY KEYWORD SEARCH
        // =========================
        String keyword = request.getParameter("tenKhachSan");

        ArrayList<KhachSan> listKhachSan;

        // =========================
        // 2. GỌI SERVICE (ĐÃ FIX)
        // =========================
        if (keyword == null || keyword.trim().isEmpty()) {
            listKhachSan = service.getAllKhachSan();
        } else {
            listKhachSan = service.searchKhachSan(keyword);
        }

        // =========================
        // 3. SET ATTRIBUTE CHO JSP
        // =========================
        request.setAttribute("listKhachSan", listKhachSan);
        request.setAttribute("tenKhachSan", keyword);

        // =========================
        // 4. FORWARD JSP
        // =========================
        request.getRequestDispatcher("/TrangChu.jsp")
               .forward(request, response);
    }
}
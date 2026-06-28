package controller;

import service.TraCuuPhongService;
import service.ThongTinHeThongService;
import model.KhachSan;
import model.LoaiPhong;
import model.PhongView;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TraCuuPhongController")
public class TraCuuPhongController extends HttpServlet {

    private final TraCuuPhongService phongService = new TraCuuPhongService();
    private final ThongTinHeThongService heThongService = new ThongTinHeThongService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // =========================
        // 1. PARSE SAFE PARAM
        // =========================
        int maKhachSan = 0;
        Integer maLoaiPhong = null;

        try {
            String ks = request.getParameter("maKhachSan");
            if (ks != null && !ks.isEmpty()) {
                maKhachSan = Integer.parseInt(ks);
            }

            String lp = request.getParameter("maLoaiPhong");
            if (lp != null && !lp.isEmpty()) {
                maLoaiPhong = Integer.parseInt(lp);
            }

        } catch (Exception e) {
            maKhachSan = 0;
            maLoaiPhong = null;
        }

        // =========================
        // 2. INIT SAFE LIST (TRÁNH NULL JSP CRASH)
        // =========================
        List<PhongView> listPhong = new ArrayList<>();
        List<LoaiPhong> listLoaiPhong = new ArrayList<>();
        KhachSan khachSan = null;

        // =========================
        // 3. CHECK VALID KHÁCH SẠN
        // =========================
        if (maKhachSan > 0) {

            khachSan = heThongService.getKhachSanById(maKhachSan);

            if (khachSan != null) {

                listLoaiPhong = heThongService.getLoaiPhongByKhachSan(maKhachSan);

                if (maLoaiPhong == null) {
                    listPhong = phongService.getAll(maKhachSan);
                } else {
                    listPhong = phongService.getPhong(maKhachSan, maLoaiPhong);
                }
            }
        }

        // =========================
        // 4. SET ATTRIBUTE SAFE
        // =========================
        request.setAttribute("khachSan", khachSan);
        request.setAttribute("listLoaiPhong", listLoaiPhong);
        request.setAttribute("listPhong", listPhong);

        request.setAttribute("maKhachSan", maKhachSan);
        request.setAttribute("maLoaiPhong", maLoaiPhong);

        // =========================
        // 5. FORWARD
        // =========================
        request.getRequestDispatcher("/TraCuuPhong.jsp")
                .forward(request, response);
    }
}
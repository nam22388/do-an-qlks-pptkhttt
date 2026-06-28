package service;

import dao.DangNhapDAO;
import model.User;

public class DangNhapService {

    private final DangNhapDAO dangNhapDAO;

    public DangNhapService() {
        this.dangNhapDAO = new DangNhapDAO();
    }

    // =========================
    // Xử lý đăng nhập
    // =========================
    public User dangNhap(String tenUser, String matKhau) {

        // 1. Validate input
        if (tenUser == null || tenUser.trim().isEmpty()) {
            return null;
        }

        if (matKhau == null || matKhau.trim().isEmpty()) {
            return null;
        }

        // 2. Gọi DAO kiểm tra DB
        User user = dangNhapDAO.login(tenUser, matKhau);

        // 3. Xử lý logic nghiệp vụ
        if (user == null) {
            return null; // sai tài khoản hoặc mật khẩu
        }

        return user;
    }
}
package service;

import dao.DangKyDAO;
import model.KhachHang;
import model.User;

public class DangKyService {

    private final DangKyDAO dangKyDAO;

    public DangKyService() {
        this.dangKyDAO = new DangKyDAO();
    }

    // =========================
    // Kiểm tra username đã tồn tại chưa
    // =========================
    public boolean checkTenUser(String tenUser) {
        return dangKyDAO.checkTenUser(tenUser);
    }

    // =========================
    // Xử lý đăng ký tài khoản
    // =========================
    public String dangKy(KhachHang kh, User user) {

        // 1. Validate dữ liệu cơ bản
        if (kh == null || user == null) {
            return "Dữ liệu không hợp lệ";
        }

        if (user.getTenUser() == null || user.getTenUser().isEmpty()) {
            return "Tên đăng nhập không được để trống";
        }

        if (user.getMatKhau() == null || user.getMatKhau().length() < 3) {
            return "Mật khẩu phải có ít nhất 3 ký tự";
        }

        if (checkTenUser(user.getTenUser())) {
            return "Tên đăng nhập đã tồn tại";
        }

        if (kh.getEmail() == null || kh.getEmail().isEmpty()) {
            return "Email không được để trống";
        }

        // 2. Gọi DAO xử lý DB
        boolean result = dangKyDAO.register(kh, user);

        // 3. Trả kết quả nghiệp vụ
        if (result) {
            return "Đăng ký thành công";
        } else {
            return "Đăng ký thất bại";
        }
    }
}
package com.web.jsp_coffee.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import com.web.jsp_coffee.model.Auth;

@WebServlet(name = "dang-ky", urlPatterns = {"/dang-ky"})
public class RegisterController extends HttpServlet {
    public RegisterController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phone = req.getParameter("sdt");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        System.out.println("Check name: " + username);
        System.out.println("Check Email: " + email);
        System.out.println("Check Phone: " + phone);
        System.out.println("Check Password: " + password);
        System.out.println("Check Confirm Password: " + confirmPassword);

        if (username != null && email != null && phone != null && password != null && confirmPassword != null) {
            if (password.equals(confirmPassword)) {
                try {
                    boolean success = Auth.getInstance().registerUser(username, email, phone, password);
                    if (success) {
                        System.out.println("Đăng ky thành công!");
                        resp.sendRedirect(req.getContextPath() + "/dang-nhap");
                    } else {
                        System.out.println("Đăng ký thất bại!");
                        req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Có lỗi xảy ra trong quá trình đăng ký!");
                    req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
                }
            } else {
                System.out.println("Mật khẩu không khớp!");
                req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
            }
        } else {
            System.out.println("Vui lòng điền đầy đủ thông tin!");
            req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
        }
    }
}

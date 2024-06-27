package com.web.jsp_coffee.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import com.web.jsp_coffee.model.Auth;

@WebServlet(name = "dang-nhap", urlPatterns = {"/dang-nhap"})
public class AuthController extends HttpServlet {

    public AuthController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("Check Email: " + email);
        System.out.println("Check Password: " + password);

        // check if email and password are not null
        if (email != null && password != null) {
            try {
                Boolean check = Auth.getInstance().checkLogin(email, password);
                if (check) {
                    // login success
                    System.out.println("Đăng nhập thành công!");

                    resp.sendRedirect(req.getContextPath() + "/trang-chu");
                } else {
                    // login failed
                    System.out.println("Đăng nhập thất bại!");
                    req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                System.out.println("Đã xảy ra lỗi khi đăng nhập!");
                e.printStackTrace();

                req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
            }
        } else {
            // email or password is null
            System.out.println("Email và mật khẩu không được để trống!");

            req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
        }
    }



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}

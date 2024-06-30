package com.web.jsp_coffee.controller;

import com.web.jsp_coffee.dao.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import com.web.jsp_coffee.model.Auth;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "dang-nhap", urlPatterns = {"/dang-nhap", "/logout"})
public class AuthController extends HttpServlet {

    public AuthController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lay url hien tai
        String url = req.getServletPath();
        if (url.equals("/logout")) {
            HttpSession session = req.getSession();
            session.removeAttribute("user");
        }
        req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email != null && password != null) {
            try {
                Boolean check = Auth.getInstance().checkLogin(email, password);
                if (check) {
                    // login success
                    HttpSession section = req.getSession();
                    User user = Auth.getInstance().getUserByEmail(email);
                    section.setAttribute("user", user);
                    if(user.getRole().equals("Admin")) {
                        resp.sendRedirect(req.getContextPath() + "/admin");
                        return;
                    }
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

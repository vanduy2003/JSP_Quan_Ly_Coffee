package com.web.jsp_coffee.controller;

import com.web.jsp_coffee.dao.User;
import com.web.jsp_coffee.dao.Product;
import com.web.jsp_coffee.model.Auth;
import com.web.jsp_coffee.model.UserModel;
import com.web.jsp_coffee.model.ProductModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "admin", urlPatterns = { "/admin", "/admin/quan-ly-san-pham", "/admin/quan-ly-san-pham/add-product", "/admin/quan-ly-san-pham/update", "/admin/quan-ly-nguoi-dung",
        "/admin/quan-ly-nguoi-dung/add-user","/admin/quan-ly-san-pham/delete", "/admin/quan-ly-nguoi-dung/delete", "/admin/quan-ly-nguoi-dung/update"})
public class AdminController extends HttpServlet {
    public AdminController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/add-user")) {
            String Name = req.getParameter("Name");
            String DateOfBirth = req.getParameter("DateOfBirth");
            String Phone = req.getParameter("Phone");
            String Email = req.getParameter("Email");
            String Password = req.getParameter("Password");
            String Address = req.getParameter("Address");
            String Role = req.getParameter("Role");

            // validate
            if (Name == null || Name.isEmpty() || DateOfBirth == null || DateOfBirth.isEmpty() || Phone == null
                    || Phone.isEmpty() || Email == null || Email.isEmpty() || Password == null || Password.isEmpty()
                    || Address == null || Address.isEmpty() || Role == null || Role.isEmpty()) {
                req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
                req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
                return;
            }
            try {
                User user = new User();
                user.setName(Name);
                user.setDateOfBirth(DateOfBirth);
                user.setPhone(Phone);
                user.setEmail(Email);
                user.setPassword(Password);
                user.setAddress(Address);
                user.setRole(Role);
                UserModel.getInstance().insert(user);
                UserModel.getInstance().closeConnection();
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-nguoi-dung");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/delete")) {
            try {
                int UserID = Integer.parseInt(req.getParameter("UserID"));
                UserModel.getInstance().delete(UserID);
                UserModel.getInstance().closeConnection();
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-nguoi-dung");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/update")) {
            try {
                int UserID = Integer.parseInt(req.getParameter("UserID"));
                String Name = req.getParameter("Name");
                String DateOfBirth = req.getParameter("DateOfBirth");
                String Phone = req.getParameter("Phone");
                String Email = req.getParameter("Email");
                String Password = req.getParameter("Password");
                String Address = req.getParameter("Address");
                String Role = req.getParameter("Role");

                User user = new User();
                user.setUserID(UserID);
                user.setName(Name);
                user.setDateOfBirth(DateOfBirth);
                user.setPhone(Phone);
                user.setEmail(Email);
                user.setPassword(Password);
                user.setAddress(Address);
                user.setRole(Role);

                UserModel.getInstance().update(user);
                UserModel.getInstance().closeConnection();
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-nguoi-dung");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham/add-product")) {
            String Name = req.getParameter("Name");
            String Price = req.getParameter("Price");
            String MoTa = req.getParameter("MoTa");
            String Category = req.getParameter("Category");
            String StockQuantity = req.getParameter("StockQuantity");
            String ImageURL = req.getParameter("ImageURL");
            String SupplierID = req.getParameter("SupplierID");
            String IsAvailable = req.getParameter("IsAvailable");

            // validate
            if (Name == null || Name.isEmpty() || Price == null || Price.isEmpty() || MoTa == null || MoTa.isEmpty()
                    || Category == null || Category.isEmpty() || StockQuantity == null || StockQuantity.isEmpty()
                    || ImageURL == null || ImageURL.isEmpty() || SupplierID == null || SupplierID.isEmpty()
                    || IsAvailable == null || IsAvailable.isEmpty()) {
                req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
                req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
                return;
            }
            try {
                Product product = new Product();
                product.setName(Name);
                product.setPrice(Price);
                product.setMoTa(MoTa);
                product.setCategory(Category);
                product.setStockQuantity(StockQuantity);
                product.setImageURL(ImageURL);
                product.setSupplierID(Integer.parseInt(SupplierID));
                product.setIsAvailable(IsAvailable);
                ProductModel.getInstance().insert(product);
                ProductModel.getInstance().closeConnection();
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham/update")) {
            try {
                int ProductID = Integer.parseInt(req.getParameter("ProductID"));
                String Name = req.getParameter("Name");
                String Price = req.getParameter("Price");
                String MoTa = req.getParameter("MoTa");
                String Category = req.getParameter("Category");
                String StockQuantity = req.getParameter("StockQuantity");
                String ImageURL = req.getParameter("ImageURL");
                String SupplierID = req.getParameter("SupplierID");
                String IsAvailable = req.getParameter("IsAvailable");

                Product product = new Product();
                product.setProductID(ProductID);
                product.setName(Name);
                product.setPrice(Price);
                product.setMoTa(MoTa);
                product.setCategory(Category);
                product.setStockQuantity(StockQuantity);
                product.setImageURL(ImageURL);
                product.setSupplierID(Integer.parseInt(SupplierID));
                product.setIsAvailable(IsAvailable);

                ProductModel.getInstance().update(product);
                ProductModel.getInstance().closeConnection();
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham/delete")) {
            try {
                int ProductID = Integer.parseInt(req.getParameter("ProductID"));
                ProductModel.getInstance().delete(ProductID);
                ProductModel.getInstance().closeConnection();
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check url
        String url = req.getRequestURI();
        if (url.equalsIgnoreCase(req.getContextPath() + "/admin")) {
            req.setAttribute("home", "home");
            req.removeAttribute("quanLySanPham");
            req.removeAttribute("quanLyNguoiDung");
        } else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham")) {
            try {
                ArrayList<Product> listProduct = ProductModel.getInstance().selectAll();
                ProductModel.getInstance().closeConnection();
                System.out.println("listProduct: " + listProduct);
                req.setAttribute("listProduct", listProduct);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            req.setAttribute("quanLySanPham", "quanLySanPham");
            req.removeAttribute("home");
            req.removeAttribute("quanLyNguoiDung");
        } else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung")
                || url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/add-user")) {
            try {
                ArrayList<User> listUser = UserModel.getInstance().selectAll();
                UserModel.getInstance().closeConnection();
                System.out.println("listUser: " + listUser);
                req.setAttribute("listUser", listUser);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            req.setAttribute("quanLyNguoiDung", "quanLyNguoiDung");
            req.removeAttribute("home");
            req.removeAttribute("quanLySanPham");
        }
        req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
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

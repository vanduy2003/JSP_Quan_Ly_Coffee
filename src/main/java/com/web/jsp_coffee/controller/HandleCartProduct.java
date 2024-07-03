package com.web.jsp_coffee.controller;

import com.web.jsp_coffee.dao.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.web.jsp_coffee.model.ProductModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;

@WebServlet(name = "cart", urlPatterns = {"/cart", "/add-cart", "/remove-cart", "/update-cart", "/checkout"})
public class HandleCartProduct extends HttpServlet {
    public HandleCartProduct() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        HttpSession session = req.getSession();

        switch (path) {
            case "/cart":
                // lay so luong san pham trong gio hang neu chua co thi la 0
                int count = 0;
                ArrayList<Product> cartquantity = (ArrayList<Product>) session.getAttribute("cart");
                if (cartquantity != null) {
                    for (Product product : cartquantity) {
                        count += product.getQuantity();
                    }
                }
                req.setAttribute("count", count);

                req.getRequestDispatcher("cart.jsp").forward(req, resp);
                break;
            case "/add-cart":
                String idProduct  = req.getParameter("idProduct");
                String quantity = req.getParameter("quantity");
                // check xem co can pham do khong
                if (idProduct == null || quantity == null) {
                    req.setAttribute("error", "Product ID or Quantity is null");
                    break;
                }else {
                    if (session.getAttribute("cart") == null) {

                        ArrayList<Product> cart = new ArrayList<>();
                        // select product by id
                        try {
                            Product product = ProductModel.getInstance().selectById(String.valueOf(Integer.parseInt(idProduct)));
                            if (product != null) {
                                product.setQuantity(Integer.parseInt(quantity));
                                cart.add(product);
                                session.setAttribute("cart", cart);
                            }
                        }catch (Exception ex) {
                            System.out.println("Error: " + ex.getMessage());
                            ex.printStackTrace();
                        }

                    }else {
                        ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
                        boolean isExist = false;
                        for (Product product : cart) {
                            if (product.getProductID() == Integer.parseInt(idProduct)) {
                                product.setQuantity(product.getQuantity() + Integer.parseInt(quantity));
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            try {
                                Product product = ProductModel.getInstance().selectById(String.valueOf(Integer.parseInt(idProduct)));
                                if (product != null) {
                                    product.setQuantity(Integer.parseInt(quantity));
                                    cart.add(product);
                                    session.setAttribute("cart", cart);
                                }
                            }catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                // cap nhat so luong san pham trong gio hang
                int count_add = 0;
                ArrayList<Product> cart_add = (ArrayList<Product>) session.getAttribute("cart");
                if (cart_add != null) {
                    for (Product product : cart_add) {
                        count_add += product.getQuantity();
                    }
                }
                req.setAttribute("count", count_add);
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;
            case "/remove-cart":
                String idProductRemove = req.getParameter("idProduct");
                if (idProductRemove == null) {
                    req.setAttribute("error", "Product ID is null");
                    break;
                }else {
                    ArrayList<Product> cart_remove = (ArrayList<Product>) session.getAttribute("cart");
                    if (cart_remove != null) {
                        for (Product product : cart_remove) {
                            if (product.getProductID() == Integer.parseInt(idProductRemove)) {
                                cart_remove.remove(product);
                                break;
                            }
                        }
                        session.setAttribute("cart", cart_remove);
                        resp.sendRedirect(req.getContextPath() + "/cart");
                    }
                }
                // cap nhat so luong san pham trong gio hang
                int count_remove = 0;
                ArrayList<Product> cart_remove = (ArrayList<Product>) session.getAttribute("cart");
                if (cart_remove != null) {
                    for (Product product : cart_remove) {
                        count_remove += product.getQuantity();
                    }
                }
                req.setAttribute("count", count_remove);
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;
            case "/update-cart":
                String idProductUpdate = req.getParameter("idProduct");
                String quantityUpdate = req.getParameter("quantity");
                if (idProductUpdate == null || quantityUpdate == null) {
                    req.setAttribute("error", "Product ID or Quantity is null");
                    break;
                }else {
                    ArrayList<Product> cart_update = (ArrayList<Product>) session.getAttribute("cart");
                    if (cart_update != null) {
                        for (Product product : cart_update) {
                            if (product.getProductID() == Integer.parseInt(idProductUpdate)) {
                                if (Integer.parseInt(quantityUpdate) == 0) {
                                    cart_update.remove(product);
                                }else {
                                    product.setQuantity(Integer.parseInt(quantityUpdate));
                                }
                                break;
                            }
                        }
                        session.setAttribute("cart", cart_update);
                    }
                }
                // in ra danh sach san pham trong gio hang
                ArrayList<Product> cart_update = (ArrayList<Product>) req.getSession().getAttribute("cart");
                if (cart_update != null) {
                    // dem so luong san pham trong gio hang
                    int count_update = 0;
                    for (Product product : cart_update) {
                        count_update += product.getQuantity();
                    }
                    req.setAttribute("count", count_update);
                }



                resp.sendRedirect(req.getContextPath() + "/cart");
                break;
            case "/checkout":
                // tinh tong tien
                ArrayList<Product> cart_checkout = (ArrayList<Product>) session.getAttribute("cart");
                if (cart_checkout != null) {
                    // dem so luong san pham trong gio hang
                    int count_checkout = 0;
                    double total = 0;
                    for (Product product : cart_checkout) {
                        total += Float.parseFloat(product.getPrice()) * product.getQuantity();
                        count_checkout += product.getQuantity();
                    }
                    req.setAttribute("count", count_checkout);
                    req.setAttribute("total", total);
                }

                req.getRequestDispatcher("checkout.jsp").forward(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/trang-chu");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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

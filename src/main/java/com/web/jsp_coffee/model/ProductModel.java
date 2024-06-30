package com.web.jsp_coffee.model;
import com.web.jsp_coffee.dao.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel implements CommonDao <Product>{
    private Connection connection;

    public ProductModel() throws SQLException {
         connection = DBUtil.getConnection();
    }

    public static ProductModel getInstance() throws SQLException {
        return new ProductModel();
    }

    // close connection
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean insert(Product T) {
        // insert product to database
        try {
            String query = String.format("INSERT INTO products (Name, Price, MoTa, Category, StockQuantity, ImageURL, SupplierID, IsAvailable) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%d', '%s')",
                    T.getName(), T.getPrice(), T.getMoTa(), T.getCategory(), T.getStockQuantity(), T.getImageURL(), T.getSupplierID(), T.getIsAvailable());
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query) > 0;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product T) {
         try {
                String query = String.format("UPDATE products SET Name = '%s', Price = '%s', MoTa = '%s', Category = '%s', StockQuantity = '%s', ImageURL = '%s', SupplierID = '%d', IsAvailable = '%s' WHERE ProductID = '%d'",
                      T.getName(), T.getPrice(), T.getMoTa(), T.getCategory(), T.getStockQuantity(), T.getImageURL(), T.getSupplierID(), T.getIsAvailable(), T.getProductID());
                Statement statement = connection.createStatement();
                return  statement.executeUpdate(query)  > 0;
         }catch (Exception ex) {
              ex.printStackTrace();
              return false;
         }
    }

    @Override
    public boolean delete(int id) {
        try {
            String query = String.format("DELETE FROM products WHERE ProductID = %d", id);
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query) > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Product selectById(String id) {
        return null;
    }

    public ArrayList<Product> selectAll() {
        try {
            String query = "SELECT * FROM products";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ArrayList<Product> listProducts = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt("ProductID"));
                product.setName(resultSet.getString("Name"));
                product.setPrice(resultSet.getString("Price"));
                product.setMoTa(resultSet.getString("MoTa"));
                product.setCategory(resultSet.getString("Category"));
                product.setStockQuantity(resultSet.getString("StockQuantity"));
                product.setImageURL(resultSet.getString("ImageURL"));
                product.setSupplierID(resultSet.getInt("SupplierID"));
                product.setIsAvailable(resultSet.getString("IsAvailable"));
                listProducts.add(product);
            }
            return listProducts;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

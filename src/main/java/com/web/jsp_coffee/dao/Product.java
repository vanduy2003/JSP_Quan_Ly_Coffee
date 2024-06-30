package com.web.jsp_coffee.dao;

public class Product {
    private int ProductID;
    private String Name;
    private String Price;
    private String MoTa;
    private String Category;
    private String StockQuantity;
    private String ImageURL;
    private int SupplierID;
    private String IsAvailable;

    public Product() {
    }

    public Product(int ProductID, String Name, String Price, String MoTa, String Category, String StockQuantity, String ImageURL, int SupplierID, String IsAvailable) {
        this.ProductID = ProductID;
        this.Name = Name;
        this.Price = Price;
        this.MoTa = MoTa;
        this.Category = Category;
        this.StockQuantity = StockQuantity;
        this.ImageURL = ImageURL;
        this.SupplierID = SupplierID;
        this.IsAvailable = IsAvailable;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getStockQuantity() {
        return StockQuantity;
    }

    public void setStockQuantity(String StockQuantity) {
        this.StockQuantity = StockQuantity;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int SupplierID) {
        this.SupplierID = SupplierID;
    }

    public String getIsAvailable() {
        return IsAvailable;
    }

    public void setIsAvailable(String IsAvailable) {
        this.IsAvailable = IsAvailable;
    }

}

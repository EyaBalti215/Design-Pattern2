package Patron_conception_specification_ex1;

public class Product {

    private String name;
    private double price;
    private String category;
    private boolean inStock;

    public Product(String name, double price, String category, boolean inStock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public boolean isInStock() {
        return inStock;
    }
}

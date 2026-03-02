package Patron_conception_specification_ex1;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("Laptop", 1500, "Electronics", true),
                new Product("Phone", 800, "Electronics", false),
                new Product("Shoes", 120, "Fashion", true)
        );

        BetterFilter bf = new BetterFilter();

        Specification<Product> priceSpec = new PriceSpecification(500);
        Specification<Product> stockSpec = new InStockSpecification();

        Specification<Product> combined =
                new AndSpecification<>(priceSpec, stockSpec);

        List<Product> result = bf.filter(products, combined);

        for (Product p : result) {
            System.out.println(p.getName());
        }
    }
}

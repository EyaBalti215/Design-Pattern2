package Patron_conception_specification_ex1;

public class PriceSpecification implements Specification<Product> {

    private double minPrice;

    public PriceSpecification(double minPrice) {
        this.minPrice = minPrice;
    }

    @Override
    public boolean isSatisfiedBy(Product p) {
        return p.getPrice() >= minPrice;
    }
}

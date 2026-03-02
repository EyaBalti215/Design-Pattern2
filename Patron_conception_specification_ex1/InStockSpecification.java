package Patron_conception_specification_ex1;

public class InStockSpecification implements Specification<Product> {

    @Override
    public boolean isSatisfiedBy(Product p) {
        return p.isInStock();
    }
}

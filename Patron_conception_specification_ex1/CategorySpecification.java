package Patron_conception_specification_ex1;

public class CategorySpecification implements Specification<Product> {

    private String category;

    public CategorySpecification(String category) {
        this.category = category;
    }

    @Override
    public boolean isSatisfiedBy(Product p) {
        return p.getCategory().equals(category);
    }
}

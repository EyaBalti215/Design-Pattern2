package Patron_conception_specification_ex1;

public class AndSpecification<T> implements Specification<T> {

    private Specification<T> spec1;
    private Specification<T> spec2;

    public AndSpecification(Specification<T> spec1, Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return spec1.isSatisfiedBy(candidate)
                && spec2.isSatisfiedBy(candidate);
    }
}

package Patron_conception_specification_ex2.specification;

import Patron_conception_specification_ex2.model.Candidate;

public class AndSpecification implements Specification {
    private Specification left;
    private Specification right;

    public AndSpecification(Specification left, Specification right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfiedBy(Candidate candidate) {
        return left.isSatisfiedBy(candidate) && right.isSatisfiedBy(candidate);
    }

    @Override
    public Specification and(Specification specification) {
        return new AndSpecification(this, specification);
    }
}

package Patron_conception_specification_ex2.specification;

import Patron_conception_specification_ex2.model.Candidate;

public class AgeSpecification implements Specification {
    private int minAge;

    public AgeSpecification(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public boolean isSatisfiedBy(Candidate candidate) {
        return candidate.getAge() >= minAge;
    }

    @Override
    public Specification and(Specification specification) {
        return new AndSpecification(this, specification);
    }

    @Override
    public String toString() {
        return "Age >= " + minAge;
    }
}

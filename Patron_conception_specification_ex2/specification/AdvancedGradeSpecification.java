package Patron_conception_specification_ex2.specification;

import Patron_conception_specification_ex2.model.Candidate;

public class AdvancedGradeSpecification implements Specification {
    private double minGrade;

    public AdvancedGradeSpecification(double minGrade) {
        this.minGrade = minGrade;
    }

    @Override
    public boolean isSatisfiedBy(Candidate candidate) {
        return candidate.getGrade() >= minGrade;
    }

    @Override
    public Specification and(Specification specification) {
        return new AndSpecification(this, specification);
    }

    @Override
    public String toString() {
        return "Advanced Grade >= " + minGrade;
    }
}

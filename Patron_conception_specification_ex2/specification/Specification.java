package Patron_conception_specification_ex2.specification;

import Patron_conception_specification_ex2.model.Candidate;

public interface Specification {
    boolean isSatisfiedBy(Candidate candidate);
    Specification and(Specification specification);
}

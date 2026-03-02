package Patron_conception_specification_ex1;

public interface Specification<T> {
    boolean isSatisfiedBy(T candidate);
}

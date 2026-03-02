package Patron_conception_specification_ex1;
import java.util.ArrayList;
import java.util.List;

public class BetterFilter {

    public <T> List<T> filter(List<T> items, Specification<T> spec) {

        List<T> result = new ArrayList<>();

        for (T item : items) {
            if (spec.isSatisfiedBy(item)) {
                result.add(item);
            }
        }

        return result;
    }
}



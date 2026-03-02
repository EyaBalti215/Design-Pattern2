package Patron_conception_specification_ex2.service;

import Patron_conception_specification_ex2.model.Candidate;
import Patron_conception_specification_ex2.specification.Specification;
import java.util.ArrayList;
import java.util.List;

public class CandidateFilterService {

    public List<Candidate> filterCandidates(List<Candidate> candidates, Specification specification) {
        List<Candidate> filteredCandidates = new ArrayList<>();
        for (Candidate candidate : candidates) {
            if (specification.isSatisfiedBy(candidate)) {
                filteredCandidates.add(candidate);
            }
        }
        return filteredCandidates;
    }

    public boolean isCandidateEligible(Candidate candidate, Specification specification) {
        return specification.isSatisfiedBy(candidate);
    }
}

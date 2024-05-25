package pt.ipp.isep.dei.project.domain;

import java.util.Comparator;
import java.util.List;

public class SortByType implements SortAlgorithms {
    @Override
    public List<GreenSpace> sort(List<GreenSpace> g) {
        g.sort(Comparator.comparing(GreenSpace::getType));
        return g;
    }
}

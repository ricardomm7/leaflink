package pt.ipp.isep.dei.project.domain;

import java.util.Comparator;
import java.util.List;

public class SortByAreaAscending implements SortAlgorithms {
    @Override
    public List<GreenSpace> sort(List<GreenSpace> g) {
        g.sort(Comparator.comparingDouble(GreenSpace::getArea));
        return g;
    }
}

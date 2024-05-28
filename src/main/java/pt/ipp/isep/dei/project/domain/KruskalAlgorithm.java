package pt.ipp.isep.dei.project.domain;

import java.util.ArrayList;
import java.util.List;

public class KruskalAlgorithm {

    public static List<Route> findMinimumSpanningTree(List<Route> allRoutes) {
        List<Route> mstRoutes = new ArrayList<>();
        bubbleSort(allRoutes);

        List<List<String>> sets = new ArrayList<>();
        for (Route route : allRoutes) {
            String startPointID = route.getStartPoint().getID();
            String endPointID = route.getEndPoint().getID();

            int indexStartPoint = findSet(startPointID, sets);
            int indexEndPoint = findSet(endPointID, sets);

            if (indexStartPoint != indexEndPoint) {
                mstRoutes.add(route);
                unionSets(indexStartPoint, indexEndPoint, sets);
            }
        }
        return mstRoutes;
    }

    private static void bubbleSort(List<Route> routes) {
        int n = routes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (routes.get(j).getCost() > routes.get(j + 1).getCost()) {
                    // Swap routes[j] with routes[j+1]
                    Route temp = routes.get(j);
                    routes.set(j, routes.get(j + 1));
                    routes.set(j + 1, temp);
                }
            }
        }
    }

    private static int findSet(String pointID, List<List<String>> sets) {
        for (int i = 0; i < sets.size(); i++) {
            List<String> set = sets.get(i);
            for (String item : set) {
                if (item.equals(pointID)) {
                    return i;
                }
            }
        }
        List<String> newSet = new ArrayList<>();
        newSet.add(pointID);
        sets.add(newSet);
        return sets.size() - 1;
    }

    private static void unionSets(int index1, int index2, List<List<String>> sets) {
        sets.get(index1).addAll(sets.get(index2));
        sets.remove(index2);
    }
}

package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.domain.SortAlgorithms;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GreenSpaceRepository implements Serializable {

    private final List<GreenSpace> greenSpaceList;

    public GreenSpaceRepository() {
        greenSpaceList = new ArrayList<>();
    }

    public void create(GreenSpaceDto d) {
        GreenSpace g = GreenSpaceMapper.toDomain(d);
        if (checkForDuplicates(g)) {
            addGreenSpace(g);
        } else {
            throw new IllegalArgumentException("There is already a green space with that name.");
        }
    }

    private void addGreenSpace(GreenSpace g) {
        greenSpaceList.add(g);
    }

    private boolean checkForDuplicates(GreenSpace g) {
        for (GreenSpace x : greenSpaceList) {
            if (x.getName().equalsIgnoreCase(g.getName())) {
                return false;
            }
        }
        return true;
    }

    public List<GreenSpaceDto> getGreenSpaceList() {
        return GreenSpaceMapper.toDtoList(greenSpaceList);
    }

    public List<GreenSpaceDto> getOrganizedList(UserSession loggedUser) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<GreenSpace> listMatched = matchWithLoggedUser(loggedUser);

        // Get the sorting algorithm class name from ApplicationSession
        String algorithmClassName = ApplicationSession.getInstance().getSortAlgorithmClassName();

        // Dynamically load the sorting algorithm class
        Class<?> algorithmClass = Class.forName(algorithmClassName);
        SortAlgorithms sorter = (SortAlgorithms) algorithmClass.newInstance();

        if (!verifyEmptyList(listMatched)) {
            listMatched = sorter.sort(listMatched);
            return GreenSpaceMapper.toDtoList(listMatched);
        } else {
            throw new IllegalArgumentException("The matched list is empty.");
        }
    }

    private boolean verifyEmptyList(List<GreenSpace> listMatched) {
        return listMatched.isEmpty();
    }

    private List<GreenSpace> matchWithLoggedUser(UserSession loggedUser) {
        List<GreenSpace> u = new ArrayList<>();
        for (GreenSpace g : greenSpaceList) {
            if (g.getManager() != null && g.getManager().getUserEmail().equalsIgnoreCase(loggedUser.getUserEmail())) {
                u.add(g);
            }
        }
        return u;
    }
}

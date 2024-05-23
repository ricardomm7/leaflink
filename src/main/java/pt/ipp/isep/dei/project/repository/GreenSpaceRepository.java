package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a repository for storing green spaces.
 */
public class GreenSpaceRepository {

    private final List<GreenSpace> greenSpaceList;

    /**
     * Constructs a new GreenSpaceRepository object with an empty list.
     */
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

    /**
     * Adds the specified green space to the repository.
     *
     * @param g the green space to be added
     */
    private void addGreenSpace(GreenSpace g) {
        greenSpaceList.add(g);
    }

    /**
     * Checks if a green space with the same name already exists in the repository.
     *
     * @param g the green space to be checked
     * @return true if no green space with the same name exists, false otherwise
     */
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

    public List<GreenSpaceDto> getOrganizedList(String algorithm, UserSession loggedUser) {
        List<GreenSpace> l = matchWithLoggedUser(loggedUser);
        if (!verifyEmptyList(l)) {
            //FALTA AQUI O MÉTODO PARA ORDENAR A LISTA (BUSCAR DE FORA)
            return GreenSpaceMapper.toDtoList(l);
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
            if (g.getManager().getUserEmail().equalsIgnoreCase(loggedUser.getUserEmail())) {
                u.add(g);
            }
        }
        return u;
    }
}

package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class ListGreenSpacesController {
    private final Repositories repositories;
    private final GreenSpaceRepository greenSpaceRepository;

    public ListGreenSpacesController() {
        this.repositories = Repositories.getInstance();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
    }

    public List<GreenSpaceDto> getList(String algorithm, UserSession loggedUser) {
        try {
            return greenSpaceRepository.getOrganizedList(algorithm, loggedUser);
        } catch (IllegalArgumentException e) {
            System.out.println("Não há espaços verdes associados ao usuário logado.");
            return new ArrayList<>();
        }
    }

}

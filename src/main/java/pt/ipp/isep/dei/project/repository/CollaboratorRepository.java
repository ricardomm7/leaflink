package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollaboratorRepository {
    private final List<Collaborator> collaboratorList;

    public CollaboratorRepository() {
        collaboratorList = new ArrayList<>();
    }

    public void create(String name, Date birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, String documentType, String identificationNumber, Date admissionDate, Job job) {
        Collaborator c = new Collaborator(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);
        if (checkForDuplicates(c)) {
            addCollaborator(c);
        }
    }

    private boolean checkForDuplicates(Collaborator j) {
        for (Collaborator x : collaboratorList) {
            if (x.getTaxpayerNumber() == j.getTaxpayerNumber()) {
                return false;
            }
        }
        return true;
    }

    public List<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    public void addCollaborator(Collaborator collab) {
        collaboratorList.add(collab);
    }
}

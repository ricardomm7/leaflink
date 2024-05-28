
package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The CollaboratorMapper class is responsible for mapping between the Collaborator domain object
 * and the CollaboratorDto data transfer object.
 */
public class CollaboratorMapper implements Serializable {

    /**
     * Converts a Collaborator domain object to a CollaboratorDto object.
     *
     * @param collaborator The Collaborator domain object to be converted.
     * @return The corresponding CollaboratorDto object.
     */
    public static CollaboratorDto toDto(Collaborator collaborator) {
        return new CollaboratorDto(collaborator.getName(), collaborator.getBirthdate(), collaborator.getContactMobile(),
                collaborator.getTaxpayerNumber(), collaborator.getEmail(), collaborator.getAddress().getAddress(), collaborator.getAddress().getZipCode(),
                collaborator.getAddress().getCity(), collaborator.getDocumentType(), collaborator.getIdentificationNumber(), collaborator.getAdmissionDate(),
                collaborator.getJob(), collaborator.getSkills());
    }

    /**
     * Converts a CollaboratorDto object to a Collaborator domain object.
     *
     * @param collaboratorDto The CollaboratorDto object to be converted.
     * @return The corresponding Collaborator domain object.
     */
    public static Collaborator toDomain(CollaboratorDto collaboratorDto) {
        return new Collaborator(collaboratorDto.getName(), collaboratorDto.getBirthdate(), collaboratorDto.getContactMobile(),
                collaboratorDto.getTaxpayerNumber(), collaboratorDto.getEmail(), collaboratorDto.getAddress().getAddress(), collaboratorDto.getAddress().getZipCode(),
                collaboratorDto.getAddress().getCity(), collaboratorDto.getDocumentType(), collaboratorDto.getIdentificationNumber(), collaboratorDto.getAdmissionDate(),
                collaboratorDto.getJob());
    }

    /**
     * Converts a list of Collaborator domain objects to a list of CollaboratorDto objects.
     *
     * @param collaboratorList The list of Collaborator domain objects to be converted.
     * @return The corresponding list of CollaboratorDto objects.
     */
    public static List<CollaboratorDto> toDtoList(List<Collaborator> collaboratorList) {
        List<CollaboratorDto> collaboratorsDto = new ArrayList<>();
        for (Collaborator collaborator : collaboratorList) {
            CollaboratorDto collDto = CollaboratorMapper.toDto(collaborator);
            collaboratorsDto.add(collDto);
        }
        return collaboratorsDto;
    }

    /**
     * Converts a list of CollaboratorDto objects to a list of Collaborator domain objects.
     *
     * @param collaboratorList The list of CollaboratorDto objects to be converted.
     * @return The corresponding list of Collaborator domain objects.
     */
    public static List<Collaborator> toDomainList(List<CollaboratorDto> collaboratorList) {
        List<Collaborator> collaborators = new ArrayList<>();
        for (CollaboratorDto collDto : collaboratorList) {
            Collaborator coll = CollaboratorMapper.toDomain(collDto);
            collaborators.add(coll);
        }
        return collaborators;
    }
}

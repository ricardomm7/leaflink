package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Collaborator mapper.
 */
public class CollaboratorMapper {

    /**
     * To dto collaborator dto.
     *
     * @param collaborator the collaborator
     * @return the collaborator dto
     */
    public static CollaboratorDto toDto(Collaborator collaborator) {
        return new CollaboratorDto(collaborator.getName(), collaborator.getBirthdate(), collaborator.getContactMobile(),
                collaborator.getTaxpayerNumber(), collaborator.getEmail(), collaborator.getAddress().getAddress(), collaborator.getAddress().getZipCode(),
                collaborator.getAddress().getCity(), collaborator.getDocumentType(), collaborator.getIdentificationNumber(), collaborator.getAdmissionDate(),
                collaborator.getJob());
    }


    /**
     * To domain collaborator.
     *
     * @param collaboratorDto the collaborator dto
     * @return the collaborator
     */
    public static Collaborator toDomain(CollaboratorDto collaboratorDto) {
        return new Collaborator(collaboratorDto.getName(), collaboratorDto.getBirthdate(), collaboratorDto.getContactMobile(),
                collaboratorDto.getTaxpayerNumber(), collaboratorDto.getEmail(), collaboratorDto.getAddress().getAddress(), collaboratorDto.getAddress().getZipCode(),
                collaboratorDto.getAddress().getCity(), collaboratorDto.getDocumentType(), collaboratorDto.getIdentificationNumber(), collaboratorDto.getAdmissionDate(),
                collaboratorDto.getJob());
    }

    public static List<CollaboratorDto> toDtoList(List<Collaborator> collaboratorList) {
        List<CollaboratorDto> collaboratorsDto = new ArrayList<>();
        for (Collaborator collaborator : collaboratorList) {
            CollaboratorDto collDto = CollaboratorMapper.toDto(collaborator);

            collaboratorsDto.add(collDto);
        }
        return collaboratorsDto;
    }
}

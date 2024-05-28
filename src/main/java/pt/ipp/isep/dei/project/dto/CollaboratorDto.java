package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.mappers.SkillMapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The CollaboratorDto class represents a data transfer object for the Collaborator domain object.
 * It encapsulates the data related to a collaborator and provides methods to access and manipulate this data.
 */
public class CollaboratorDto implements Serializable {
    private final String name;
    private final LocalDate birthdate;
    private final int contactMobile;
    private final int taxpayerNumber;
    private final String email;
    private final DocumentType documentType;
    private final String identificationNumber;
    private final LocalDate admissionDate;
    private final Job job;
    private final Address address;
    private final List<Skill> skills;

    /**
     * Constructs a new CollaboratorDto object with the provided data.
     *
     * @param name                 the name of the collaborator
     * @param birthdate            the birthdate of the collaborator
     * @param contactMobile        the contact mobile number of the collaborator
     * @param taxpayerNumber       the taxpayer number of the collaborator
     * @param email                the email address of the collaborator
     * @param address              the address of the collaborator
     * @param zipCode              the zip code of the collaborator's address
     * @param city                 the city of the collaborator's address
     * @param documentType         the document type of the collaborator
     * @param identificationNumber the identification number of the collaborator
     * @param admissionDate        the admission date of the collaborator
     * @param job                  the job of the collaborator
     * @param skills               the list of skills of the collaborator
     */
    public CollaboratorDto(String name, LocalDate birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, DocumentType documentType, String identificationNumber, LocalDate admissionDate, Job job, List<Skill> skills) {
        this.name = name;
        this.birthdate = birthdate;
        this.contactMobile = contactMobile;
        this.taxpayerNumber = taxpayerNumber;
        this.email = email;
        this.address = new Address(address, city, zipCode);
        this.documentType = documentType;
        this.identificationNumber = identificationNumber;
        this.admissionDate = admissionDate;
        this.job = job;
        this.skills = skills;
    }

    /**
     * Constructs a new CollaboratorDto object with the provided data and an empty list of skills.
     *
     * @param name                 the name of the collaborator
     * @param birthdate            the birthdate of the collaborator
     * @param contactMobile        the contact mobile number of the collaborator
     * @param taxpayerNumber       the taxpayer number of the collaborator
     * @param email                the email address of the collaborator
     * @param address              the address of the collaborator
     * @param zipCode              the zip code of the collaborator's address
     * @param city                 the city of the collaborator's address
     * @param documentType         the document type of the collaborator
     * @param identificationNumber the identification number of the collaborator
     * @param admissionDate        the admission date of the collaborator
     * @param job                  the job of the collaborator
     */
    public CollaboratorDto(String name, LocalDate birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, DocumentType documentType, String identificationNumber, LocalDate admissionDate, Job job) {
        this.name = name;
        this.birthdate = birthdate;
        this.contactMobile = contactMobile;
        this.taxpayerNumber = taxpayerNumber;
        this.email = email;
        this.address = new Address(address, city, zipCode);
        this.documentType = documentType;
        this.identificationNumber = identificationNumber;
        this.admissionDate = admissionDate;
        this.job = job;
        this.skills = new ArrayList<>();
    }

    /**
     * Gets the birthdate of the collaborator.
     *
     * @return the birthdate of the collaborator
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Gets the name of the collaborator.
     *
     * @return the name of the collaborator
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the contact mobile number of the collaborator.
     *
     * @return the contact mobile number of the collaborator
     */
    public int getContactMobile() {
        return contactMobile;
    }

    /**
     * Gets the taxpayer number of the collaborator.
     *
     * @return the taxpayer number of the collaborator
     */
    public int getTaxpayerNumber() {
        return taxpayerNumber;
    }

    /**
     * Gets the email address of the collaborator.
     *
     * @return the email address of the collaborator
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the document type of the collaborator.
     *
     * @return the document type of the collaborator
     */
    public DocumentType getDocumentType() {
        return documentType;
    }

    /**
     * Gets the identification number of the collaborator.
     *
     * @return the identification number of the collaborator
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Gets the admission date of the collaborator.
     *
     * @return the admission date of the collaborator
     */
    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Gets the job of the collaborator.
     *
     * @return the job of the collaborator
     */
    public Job getJob() {
        return job;
    }

    /**
     * Gets the address of the collaborator.
     *
     * @return the address of the collaborator
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets the list of skills of the collaborator as a list of SkillDto objects.
     *
     * @return the list of skills of the collaborator as SkillDto objects
     */
    public List<SkillDto> getSkills() {
        List<SkillDto> e = new ArrayList<>();
        for (Skill s : skills) {
            e.add(new SkillDto(s.getDesignation()));
        }
        return e;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollaboratorDto that = (CollaboratorDto) o;
        return contactMobile == that.getContactMobile() &&
                taxpayerNumber == that.getTaxpayerNumber() &&
                Objects.equals(name, that.getName()) &&
                Objects.equals(birthdate, that.getBirthdate()) &&
                Objects.equals(email, that.getEmail()) &&
                documentType == that.getDocumentType() &&
                Objects.equals(identificationNumber, that.getIdentificationNumber()) &&
                Objects.equals(admissionDate, that.getAdmissionDate()) &&
                Objects.equals(job, that.getJob()) &&
                Objects.equals(address, that.getAddress()) &&
                Objects.equals(skills, SkillMapper.listToDomain(that.getSkills()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdate, contactMobile, taxpayerNumber, email, documentType, identificationNumber, admissionDate, job, address, skills);
    }

    @Override
    public String toString() {
        return "Collaborator{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", contactMobile=" + contactMobile +
                ", taxpayerNumber=" + taxpayerNumber +
                ", email='" + email + '\'' +
                ", documentType=" + documentType +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", admissionDate=" + admissionDate +
                ", job=" + job +
                ", address=" + address +
                ", skills=" + skills +
                '}';
    }
}

package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.domain.Skill;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Collaborator class represents an individual who collaborates within an organization.
 * It holds information such as name, birthdate, contact details, taxpayer number, email, address, identification number,
 * admission date, and job.
 */
public class CollaboratorDto implements Serializable {
    private String name;
    private LocalDate birthdate;
    private int contactMobile;
    private int taxpayerNumber;
    private String email;
    private DocumentType documentType;
    private String identificationNumber;
    private LocalDate admissionDate;
    private Job job;
    private Address address;
    private List<Skill> skills;

    /**
     * Constructor for Collaborator class.
     *
     * @param name                 The name of the collaborator.
     * @param birthdate            The birthdate of the collaborator.
     * @param contactMobile        The mobile contact number of the collaborator.
     * @param taxpayerNumber       The taxpayer number of the collaborator.
     * @param email                The email address of the collaborator.
     * @param address              The address of the collaborator.
     * @param zipCode              The zip code of the collaborator's address.
     * @param city                 The city of the collaborator's address.
     * @param documentType         The type of document of the collaborator.
     * @param identificationNumber The identification number of the collaborator.
     * @param admissionDate        The admission date of the collaborator.
     * @param job                  The job of the collaborator.
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getName() {
        return name;
    }

    public int getContactMobile() {
        return contactMobile;
    }

    public int getTaxpayerNumber() {
        return taxpayerNumber;
    }

    public String getEmail() {
        return email;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public Job getJob() {
        return job;
    }

    public Address getAddress() {
        return address;
    }

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
                Objects.equals(skills, that.getSkills());
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

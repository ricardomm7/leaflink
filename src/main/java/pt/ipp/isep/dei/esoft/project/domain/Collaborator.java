package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

public class Collaborator {
    private String name;
    private Date birthdate;
    private int contactMobile;
    private int taxpayerNumber;
    private String email;
    private String address;
    private String zipCode;
    private String city;
    private String documentType;
    private String identificationNumber;
    private Date admissionDate;
    private Job job;

    public Collaborator(String name, Date birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, String documentType, String identificationNumber, Date admissionDate, Job job) {

    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContactMobile(int contactMobile) {
        this.contactMobile = contactMobile;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaxpayerNumber(int taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

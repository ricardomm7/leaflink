package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * The Collaborator class represents an individual who collaborates within an organization.
 * It holds information such as name, birthdate, contact details, taxpayer number, email, address, identification number,
 * admission date, and job.
 */
public class Collaborator implements Serializable {
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
    private List<Skill> skills = new ArrayList<>();

    private boolean available;

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
    public Collaborator(String name, LocalDate birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, DocumentType documentType, String identificationNumber, LocalDate admissionDate, Job job) {
        this.available = true;
        try {
            setName(name);
            setBirthdate(birthdate);
            setContactMobile(contactMobile);
            setEmail(email);
            setAddress(address, city, zipCode);
            this.documentType = documentType;
            setIdentificationNumber(identificationNumber);
            setAdmissionDate(admissionDate);
            setTaxpayerNumber(taxpayerNumber);
            setJob(job);
        } catch (Exception e) {
            ShowError.showAlert("Collaborator", e.getMessage(), "Error when setting the collaborator attributes.");
        }
    }

    /**
     * Sets the address of the collaborator.
     *
     * @param address The address of the collaborator.
     * @param city    The city of the collaborator's address.
     * @param zipCode The zip code of the collaborator's address.
     */
    public void setAddress(String address, String city, String zipCode) {
        this.address = new Address(address, city, zipCode);
    }

    /**
     * Sets the admission date of the collaborator.
     *
     * @param admissionDate The admission date of the collaborator.
     * @throws IllegalArgumentException If the admission date is invalid.
     */
    public void setAdmissionDate(LocalDate admissionDate) {
        if (verifyAdmissionDate(admissionDate)) {
            this.admissionDate = admissionDate;
        } else {
            throw new IllegalArgumentException("The admission date is invalid.");
        }
    }

    /**
     * Sets the birthdate of the collaborator.
     *
     * @param birthdate the birthdate of the collaborator
     * @throws IllegalArgumentException if the birthdate is invalid
     */
    public void setBirthdate(LocalDate birthdate) {
        if (verifyBirth(birthdate)) {
            this.birthdate = birthdate;
        } else {
            throw new IllegalArgumentException("The collaborator birthdate mustn't be today or future.");
        }
    }

    /**
     * Sets the mobile contact number of the collaborator.
     *
     * @param contactMobile the mobile contact number of the collaborator
     * @throws IllegalArgumentException if the contact number is not exactly 9 digits long
     */
    public void setContactMobile(int contactMobile) {
        if (verifyNineDigits(contactMobile)) {
            this.contactMobile = contactMobile;
        } else {
            throw new IllegalArgumentException("The contact number must be exactly 9 digits long.");
        }
    }

    /**
     * Sets the email address of the collaborator.
     *
     * @param email the email address of the collaborator
     * @throws IllegalArgumentException if the email address is not valid
     */
    public void setEmail(String email) {
        if (verifyEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("The email is not valid.");
        }
    }

    /**
     * Sets the identification number of the collaborator.
     *
     * @param identificationNumber the identification number of the collaborator
     * @throws IllegalArgumentException if the identification number is not valid
     */
    public void setIdentificationNumber(String identificationNumber) {
        if (verifyIdentificationNumber(identificationNumber)) {
            this.identificationNumber = identificationNumber;
        } else {
            throw new IllegalArgumentException("For the given document type (" + this.documentType.toString() + "), the identification number is invalid. Check the user manual for more info.");
        }
    }

    /**
     * Sets available.
     *
     * @param available the boolean (available or not available)
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Checks that the identification number provided is valid according to the type of document.
     * <p>
     * For the document type {@link DocumentType#CITIZEN_CARD}, the ID number must be exactly 12 characters long,
     * with the first 9 being digits, the next 2 being letters, and the last being a digit.
     * <p>
     * For the document type {@link DocumentType#PASSPORT}, the identification number must be between 6 and 9 characters long
     * and alphanumeric.
     * <p>
     * For the document type {@link DocumentType#IDENTITY_CARD}, the ID number must be exactly 9 digits long.
     * <p>
     * For the document type {@link DocumentType#OTHER_TYPE}, the identification number must consist only of letters
     * and numbers.
     *
     * @param identificationNumber the identification number to be checked
     * @return true if the identification number is valid according to the document type, false otherwise
     */
    private boolean verifyIdentificationNumber(String identificationNumber) {
        if (identificationNumber == null || !verifyFilled(identificationNumber) || containsSpecialCharacters(identificationNumber)) {
            return false;
        }
        try {
            return this.documentType.verifyNumber(identificationNumber);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Sets the job of the collaborator.
     *
     * @param job the job of the collaborator
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * Sets the name of the collaborator.
     *
     * @param name the name of the collaborator
     * @throws IllegalArgumentException if the provided name is empty or consists of only whitespace
     */
    public void setName(String name) {
        if (verifyFilled(name) && !containsSpecialCharacters(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("The given name is invalid!");
        }
    }

    /**
     * Verifies if a string has special characters.
     *
     * @param str the analysed string
     * @return true if the string has special characters
     */
    private boolean containsSpecialCharacters(String str) {
        return !str.matches("[a-zA-Z0-9 ]*");
    }

    /**
     * Sets the taxpayer number of the collaborator.
     *
     * @param taxpayerNumber the taxpayer number of the collaborator
     * @throws IllegalArgumentException if the taxpayer number is not exactly 9 digits long
     */
    public void setTaxpayerNumber(int taxpayerNumber) {
        if (verifyNineDigits(taxpayerNumber)) {
            this.taxpayerNumber = taxpayerNumber;
        } else {
            throw new IllegalArgumentException("The Taxpayer number must be exactly 9 digits long.");
        }
    }

    /**
     * Retrieves the taxpayer number of the collaborator.
     *
     * @return the taxpayer number of the collaborator
     */
    public int getTaxpayerNumber() {
        return taxpayerNumber;
    }

    /**
     * Verifies if the field is filled.
     *
     * @param name the field analysed
     * @return true if is filled and false if it isn't
     */
    private boolean verifyFilled(String name) {
        return (!name.trim().isEmpty());
    }

    /**
     * Verifies if the birthdate is valid.
     *
     * @param date the birthdate to verify
     * @return true if the birthdate is valid, false otherwise
     */
    private boolean verifyBirth(LocalDate date) {
        if (date == null) {
            return false;
        }

        // Get today's date
        LocalDate today = LocalDate.now();

        // Verify if the date is not today or in the future
        return !date.isAfter(today);
    }

    /**
     * Verifies if the provided number has exactly nine digits.
     *
     * @param number the number to verify
     * @return true if the number has exactly nine digits, false otherwise
     */
    private boolean verifyNineDigits(int number) {
        return (String.valueOf(number).length() == 9);
    }

    /**
     * Verifies if the provided email address is valid.
     *
     * @param email the email address to verify
     * @return true if the email address is valid, false otherwise
     */
    private boolean verifyEmail(String email) {
        if (email == null) {
            return false;
        }
        // Verify if the email contains "@" and has at least one character before and after it
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        return !(atIndex <= 0 || dotIndex < atIndex + 2 || dotIndex == email.length() - 1);
    }

    /**
     * Verifies if the admission date is valid.
     *
     * @param admissionDate the admission date to verify
     * @return true if the admission date is valid, false otherwise
     */
    private boolean verifyAdmissionDate(LocalDate admissionDate) {
        if (admissionDate == null || this.birthdate == null) {
            return false;
        }

        // Verify if the admission date is not before the birthdate
        if (admissionDate.isBefore(this.birthdate)) {
            return false;
        }

        // Calculate the age difference in years between the admission date and the birthdate
        int ageDifference = Period.between(this.birthdate, admissionDate).getYears();

        // Verify if the age difference is at least 18 years
        return ageDifference >= 18;
    }

    /**
     * Gets the name of the collaborator.
     *
     * @return The name of the collaborator.
     */
    public String getName() {
        return name;
    }

    /**
     * Assigns skills to the collaborator.
     *
     * @param selectedSkills the skills to be assigned.
     */
    public void assignSkills(List<Skill> selectedSkills) {
        for (Skill skill : selectedSkills) {
            if (!this.skills.contains(skill)) {
                this.skills.add(skill);
            }
        }
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Retrieves the skills of the collaborator.
     *
     * @return the list of skills of the collaborator.
     */
    public List<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    /**
     * Retrieves the collaborator's birthdate.
     *
     * @return the collaborator birthdate.
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Gets the address of the collaborator.
     *
     * @return the address of the collaborator.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets the admission date of the collaborator.
     *
     * @return the admission date of the collaborator.
     */
    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Gets the document type of the collaborator's identification.
     *
     * @return the document type of the collaborator's identification.
     */
    public DocumentType getDocumentType() {
        return documentType;
    }

    /**
     * Gets the mobile contact number of the collaborator.
     *
     * @return the mobile contact number of the collaborator.
     */
    public int getContactMobile() {
        return contactMobile;
    }

    /**
     * Gets the job of the collaborator.
     *
     * @return the job of the collaborator.
     */
    public Job getJob() {
        return job;
    }

    /**
     * Gets the email address of the collaborator.
     *
     * @return the email address of the collaborator.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the identification number of the collaborator.
     *
     * @return the identification number of the collaborator.
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborator that = (Collaborator) o;
        return contactMobile == that.getContactMobile() &&
                taxpayerNumber == that.getTaxpayerNumber() &&
                name.equals(that.getName());
    }
}

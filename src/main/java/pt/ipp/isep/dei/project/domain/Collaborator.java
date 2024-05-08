package pt.ipp.isep.dei.project.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The Collaborator class represents an individual who collaborates within an organization.
 * It holds information such as name, birthdate, contact details, taxpayer number, email, address, identification number,
 * admission date, and job.
 */
public class Collaborator {
    private String name;
    private Date birthdate;
    private int contactMobile;
    private int taxpayerNumber;
    private String email;
    private String documentType;
    private String identificationNumber;
    private Date admissionDate;
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
    public Collaborator(String name, Date birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, String documentType, String identificationNumber, Date admissionDate, Job job) {
        setName(name);
        setBirthdate(birthdate);
        setContactMobile(contactMobile);
        setTaxpayerNumber(taxpayerNumber);
        setEmail(email);
        setAddress(address, city, zipCode);
        setDocumentType(documentType);
        setIdentificationNumber(identificationNumber);
        setAdmissionDate(admissionDate);
        setJob(job);
        skills = new ArrayList<>();
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
    public void setAdmissionDate(Date admissionDate) {
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
    public void setBirthdate(Date birthdate) {
        if (verifyBirth(birthdate)) {
            this.birthdate = birthdate;
        } else {
            throw new IllegalArgumentException("The collaborator must be at least 18 years old.");
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
     * Sets the document type of the collaborator.
     *
     * @param documentType the document type of the collaborator
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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
            throw new IllegalArgumentException("The identification number is not valid.");
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
        if (verifyFilled(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("The given name is invalid!");
        }
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
    public boolean verifyFilled(String name) {
        return (!name.trim().isEmpty());
    }

    /**
     * Verifies if the birthdate is valid.
     *
     * @param date the birthdate to verify
     * @return true if the birthdate is valid, false otherwise
     */
    public boolean verifyBirth(Date date) {
        if (date == null) {
            return false;
        }
        // Verifica se a data não é a de hoje ou futura
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        return !date.after(today.getTime());
    }

    /**
     * Verifies if the provided number has exactly nine digits.
     *
     * @param number the number to verify
     * @return true if the number has exactly nine digits, false otherwise
     */
    public boolean verifyNineDigits(int number) {
        return (String.valueOf(number).length() == 9);
    }

    /**
     * Verifies if the provided email address is valid.
     *
     * @param email the email address to verify
     * @return true if the email address is valid, false otherwise
     */
    public boolean verifyEmail(String email) {
        if (email == null) {
            return false;
        }
        // Verifica se o email contém "@" e se há pelo menos um caractere antes e depois dele
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        return !(atIndex <= 0 || dotIndex < atIndex + 2 || dotIndex == email.length() - 1);
    }

    /**
     * Verifies if the provided identification number consists of only letters and numbers.
     *
     * @param identificationNumber the identification number to verify
     * @return true if the identification number consists of only letters and numbers, false otherwise
     */
    public boolean verifyIdentificationNumber(String identificationNumber) {
        if (identificationNumber == null) {
            return false;
        }
        // Itera sobre cada caractere do número de identificação
        for (char c : identificationNumber.toCharArray()) {
            // Verifica se o caractere não é uma letra ou um número
            if (!Character.isLetterOrDigit(c)) {
                return false; // Retorna falso se encontrar um caractere que não seja letra ou número
            }
        }
        return true; // Retorna verdadeiro se todos os caracteres forem letras ou números
    }

    /**
     * Verifies if the admission date is valid.
     *
     * @param admissionDate the admission date to verify
     * @return true if the admission date is valid, false otherwise
     */
    public boolean verifyAdmissionDate(Date admissionDate) {
        if (admissionDate == null || this.birthdate == null) {
            return false;
        }
        // Verifica se a data de admissão não é anterior à data de nascimento
        if (admissionDate.before(this.birthdate)) {
            return false;
        }

        // Calcula a diferença em anos entre a data de admissão e a data de nascimento
        Calendar calendarAdmission = Calendar.getInstance();
        calendarAdmission.setTime(admissionDate);
        int yearAdmission = calendarAdmission.get(Calendar.YEAR);

        Calendar calendarBirth = Calendar.getInstance();
        calendarBirth.setTime(this.birthdate);
        int yearBirth = calendarBirth.get(Calendar.YEAR);

        int ageDifference = yearAdmission - yearBirth;

        // Verifica se a diferença de idade é pelo menos 18 anos
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
     * Assigns skills to the collaborator
     * @param selectedSkills the skills to be assigned
     */
    public void assignSkills(Skill[] selectedSkills) {
        for (Skill skill : selectedSkills) {
            if (!skills.contains(skill)) {
                skills.add(skill);
            }
        }
    }

    /**
     * retrieves the skills of the collaborator
     * @return the list of skills of the collaborator
     */
    public List<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    public Date getBirthdate() {
        return birthdate;
    }
}

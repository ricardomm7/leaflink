package pt.ipp.isep.dei.project.domain;

import java.util.Calendar;
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
        setName(name);
        setBirthdate(birthdate);
        setContactMobile(contactMobile);
        setTaxpayerNumber(taxpayerNumber);
        setEmail(email);
        setAddress(address);
        setZipCode(zipCode);
        setCity(city);
        setDocumentType(documentType);
        setIdentificationNumber(identificationNumber);
        setAdmissionDate(admissionDate);
        setJob(job);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAdmissionDate(Date admissionDate) {
        if (verifyAdmissionDate(admissionDate)) {
            this.admissionDate = admissionDate;
        } else {
            throw new IllegalArgumentException("The admission date is invalid.");
        }
    }

    public void setBirthdate(Date birthdate) {
        if (verifyBirth(birthdate)) {
            this.birthdate = birthdate;
        } else {
            throw new IllegalArgumentException("The collaborator must be at least 18 years old.");
        }
    }


    public void setCity(String city) {
        this.city = city;
    }

    public void setContactMobile(int contactMobile) {
        if (verifyNineDigits(contactMobile)) {
            this.contactMobile = contactMobile;
        } else {
            throw new IllegalArgumentException("The contact number must be exactly 9 digits long.");
        }
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setEmail(String email) {
        if (verifyEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("The email is not valid.");
        }
    }

    public void setIdentificationNumber(String identificationNumber) {
        if (verifyIdentificationNumber(identificationNumber)) {
            this.identificationNumber = identificationNumber;
        } else {
            throw new IllegalArgumentException("The identification number is not valid.");
        }
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setName(String name) {
        if (verifyFilled(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("The given name is invalid!");
        }
    }

    public void setTaxpayerNumber(int taxpayerNumber) {
        if (verifyNineDigits(taxpayerNumber)) {
            this.taxpayerNumber = taxpayerNumber;
        } else {
            throw new IllegalArgumentException("The Taxpayer number must be exactly 9 digits long.");
        }
    }

    public void setZipCode(String zipCode) {
        if (verifyZipCode(zipCode)) {
            this.zipCode = zipCode;
        } else {
            throw new IllegalArgumentException("The zip code must be in the format ####-###.");
        }
    }

    private boolean verifyFilled(String name) {
        return (!name.trim().isEmpty());
    }

    private boolean verifyBirth(Date date) {
        // Verifica se a data não é a de hoje ou futura
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        return !date.after(today.getTime());
    }

    private boolean verifyZipCode(String zipCode) {
        // Verifica se o código postal tem o comprimento correto
        if (zipCode.length() != 8 || zipCode.charAt(4) != '-') {
            return false;
        }
        // Verifica se os caracteres nas posições corretas são dígitos
        for (int i = 0; i < 8; i++) {
            if (i != 4 && !Character.isDigit(zipCode.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyNineDigits(int number) {
        return (String.valueOf(number).length() == 9);
    }

    private boolean verifyEmail(String email) {
        // Verifica se o email contém "@" e se há pelo menos um caractere antes e depois dele
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        return !(atIndex <= 0 || dotIndex < atIndex + 2 || dotIndex == email.length() - 1);
    }

    private boolean verifyIdentificationNumber(String identificationNumber) {
        // Itera sobre cada caractere do número de identificação
        for (char c : identificationNumber.toCharArray()) {
            // Verifica se o caractere não é uma letra ou um número
            if (!Character.isLetterOrDigit(c)) {
                return false; // Retorna falso se encontrar um caractere que não seja letra ou número
            }
        }
        return true; // Retorna verdadeiro se todos os caracteres forem letras ou números
    }

    private boolean verifyAdmissionDate(Date admissionDate) {
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
}

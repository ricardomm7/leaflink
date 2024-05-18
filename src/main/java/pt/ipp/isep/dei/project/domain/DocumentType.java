package pt.ipp.isep.dei.project.domain;

/**
 * Enum representing different types of identification for a person.
 */
public enum DocumentType {
    CITIZEN_CARD {
        public String toString() {
            return "Citizen Card";
        }
    },
    PASSPORT {
        public String toString() {
            return "Passport";
        }
    },
    IDENTITY_CARD {
        public String toString() {
            return "Identity Card";
        }
    },
}

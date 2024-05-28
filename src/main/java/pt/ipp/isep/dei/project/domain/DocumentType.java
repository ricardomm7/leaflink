package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;

/**
 * The DocumentType enum represents different types of identification documents.
 * Each type provides a custom implementation for verifying the document number format.
 */
public enum DocumentType implements Serializable {

    /**
     * Represents a Citizen Card.
     */
    CITIZEN_CARD {
        /**
         * Returns the string representation of the Citizen Card type.
         *
         * @return "Citizen Card"
         */
        public String toString() {
            return "Citizen Card";
        }

        /**
         * Verifies if the given number matches the Citizen Card format.
         * The format is 9 digits followed by 2 letters and 1 digit.
         *
         * @param numb the number to verify.
         * @return true if the number matches the format, false otherwise.
         */
        public boolean verifyNumber(String numb) {
            if (numb.length() != 12) {
                return false;
            }
            for (int i = 0; i < 9; i++) {
                if (!Character.isDigit(numb.charAt(i))) {
                    return false;
                }
            }
            for (int i = 9; i < 11; i++) {
                if (!Character.isLetter(numb.charAt(i))) {
                    return false;
                }
            }
            return Character.isDigit(numb.charAt(11));
        }
    },

    /**
     * Represents a Passport.
     */
    PASSPORT {
        /**
         * Returns the string representation of the Passport type.
         *
         * @return "Passport"
         */
        public String toString() {
            return "Passport";
        }

        /**
         * Verifies if the given number matches the Passport format.
         * The format is 6 to 9 alphanumeric characters.
         *
         * @param numb the number to verify.
         * @return true if the number matches the format, false otherwise.
         */
        public boolean verifyNumber(String numb) {
            int length = numb.length();
            if (length < 6 || length > 9) {
                return false;
            }
            for (char c : numb.toCharArray()) {
                if (!Character.isLetterOrDigit(c)) {
                    return false;
                }
            }
            return true;
        }
    },

    /**
     * Represents an Identity Card.
     */
    IDENTITY_CARD {
        /**
         * Returns the string representation of the Identity Card type.
         *
         * @return "Identity Card"
         */
        public String toString() {
            return "Identity Card";
        }

        /**
         * Verifies if the given number matches the Identity Card format.
         * The format is 9 digits.
         *
         * @param numb the number to verify.
         * @return true if the number matches the format, false otherwise.
         */
        public boolean verifyNumber(String numb) {
            if (numb == null || numb.length() != 9) {
                return false;
            }
            for (char c : numb.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
    },

    /**
     * Represents any other type of document.
     */
    OTHER_TYPE {
        /**
         * Returns the string representation of the Other Type document.
         *
         * @return "Other type"
         */
        public String toString() {
            return "Other type";
        }

        /**
         * Verifies if the given number matches the Other Type document format.
         * The format allows any alphanumeric characters.
         *
         * @param numb the number to verify.
         * @return true if the number matches the format, false otherwise.
         */
        public boolean verifyNumber(String numb) {
            for (char c : numb.toCharArray()) {
                if (!Character.isLetterOrDigit(c)) {
                    return false;
                }
            }
            return true;
        }
    };

    /**
     * Verifies if the given number matches the document format.
     *
     * @param numb the number to verify.
     * @return true if the number matches the format, false otherwise.
     */
    public abstract boolean verifyNumber(String numb);
}

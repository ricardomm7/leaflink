package pt.ipp.isep.dei.project.domain;

/**
 * Enum representing different types of identification for a person.
 */
public enum DocumentType {
    /**
     * The Citizen card.
     */
    CITIZEN_CARD {
        public String toString() {
            return "Citizen Card";
        }

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
     * The Passport.
     */
    PASSPORT {
        public String toString() {
            return "Passport";
        }

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
     * The Identity card.
     */
    IDENTITY_CARD {
        public String toString() {
            return "Identity Card";
        }

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
     * The Other type.
     */
    OTHER_TYPE {
        public String toString() {
            return "Other type";
        }

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
     * Verify number boolean.
     *
     * @param numb the numb
     * @return the boolean
     */
    public abstract boolean verifyNumber(String numb);
}

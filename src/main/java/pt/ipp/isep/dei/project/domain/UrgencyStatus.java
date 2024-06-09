package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;

/**
 * The UrgencyStatus enum represents different levels of urgency for tasks.
 */
public enum UrgencyStatus implements Serializable {

    /**
     * High urgency status.
     */
    HIGH {
        // Override toString method to return a more readable representation
        @Override
        public String toString() {
            return "High";
        }
    },

    /**
     * Medium urgency status.
     */
    MEDIUM {
        // Override toString method to return a more readable representation
        @Override
        public String toString() {
            return "Medium";
        }
    },

    /**
     * Low urgency status.
     */
    LOW {
        // Override toString method to return a more readable representation
        @Override
        public String toString() {
            return "Low";
        }
    }
}

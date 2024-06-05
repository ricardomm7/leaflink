package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;

/**
 * The UrgencyStatus enum represents different levels of urgency for tasks.
 */
public enum UrgencyStatus implements Serializable {

    HIGH {
        // Override toString method to return a more readable representation
        @Override
        public String toString() {
            return "High";
        }
    },
    MEDIUM {
        // Override toString method to return a more readable representation
        @Override
        public String toString() {
            return "Medium";
        }
    },
    LOW {
        // Override toString method to return a more readable representation
        @Override
        public String toString() {
            return "Low";
        }
    }
}

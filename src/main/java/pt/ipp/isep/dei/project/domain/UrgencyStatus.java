package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;

/**
 * The UrgencyStatus enum represents different levels of urgency for tasks.
 */
public enum UrgencyStatus implements Serializable {

    HIGH {
        public String toString() {
            return "High";
        }
    },
    MEDIUM {
        public String toString() {
            return "Medium";
        }
    },
    LOW {
        public String toString() {
            return "Low";
        }
    }
}

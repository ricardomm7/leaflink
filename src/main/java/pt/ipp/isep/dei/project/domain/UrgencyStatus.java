package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;

/**
 * The enum Degree of urgency.
 */
public enum UrgencyStatus implements Serializable {

    /**
     * The High.
     */
    HIGH {
        public String toString() {
            return "High";
        }
    },
    /**
     * The Medium.
     */
    MEDIUM {
        public String toString() {
            return "Medium";
        }
    },
    /**
     * The Low.
     */
    LOW {
        public String toString() {
            return "Low";
        }
    },
}

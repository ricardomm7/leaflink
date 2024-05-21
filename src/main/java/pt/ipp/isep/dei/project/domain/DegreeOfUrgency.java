package pt.ipp.isep.dei.project.domain;

/**
 * The enum Degree of urgency.
 */
public enum DegreeOfUrgency {

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

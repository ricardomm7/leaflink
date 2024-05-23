package pt.ipp.isep.dei.project.domain;

public enum Status {
        /**
     * Pending status.
     */
    PENDING {
        public String toString() {
            return "Pending";
        }
    },
    /**
     * The Planed status.
     */
    PLANED {
        public String toString() {
            return "Planed";
        }
    },
    /**
     * The Completed status.
     */
    COMPLETED {
        public String toString() {
            return "Completed";
        }
    },

    /**
     * The Postponed status.
     */
    POSTPONED {
        public String toString() {return "Postponed";}
    },

    /**
     * The Cancelled status.
     */
    CANCELLED {
        public String toString() {return "Cancelled";}
    }
}

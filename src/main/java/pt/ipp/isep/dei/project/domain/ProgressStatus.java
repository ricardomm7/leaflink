package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;

/**
 * The ProgressStatus enum represents the progress status of an agenda entry.
 * It includes the following status: PENDING, PLANED, COMPLETED, POSTPONED, and CANCELLED.
 */
public enum ProgressStatus implements Serializable {

    /**
     * Represents a pending status.
     */
    PENDING {
        public String toString() {
            return "Pending";
        }
    },

    /**
     * Represents a planned status.
     */
    PLANNED {
        public String toString() {
            return "Planned";
        }
    },

    /**
     * Represents a completed status.
     */
    COMPLETED {
        public String toString() {
            return "Completed";
        }
    },

    /**
     * Represents a postponed status.
     */
    POSTPONED {
        public String toString() {
            return "Postponed";
        }
    },

    /**
     * Represents a cancelled status.
     */
    CANCELLED {
        public String toString() {
            return "Cancelled";
        }
    }
}

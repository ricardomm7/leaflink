package pt.ipp.isep.dei.project.domain;

/**
 * The enum Green space type.
 */
public enum GreenSpaceType {
    /**
     * The Garden.
     */
    GARDEN {
        public String toString() {
            return "Garden";
        }
    },
    /**
     * The Medium sized park.
     */
    MEDIUM_SIZED_PARK {
        public String toString() {
            return "Medium-Sized Park";
        }
    },
    /**
     * The Large sized park.
     */
    LARGE_SIZED_PARK {
        public String toString() {
            return "Large-Sized Park";
        }
    },
}

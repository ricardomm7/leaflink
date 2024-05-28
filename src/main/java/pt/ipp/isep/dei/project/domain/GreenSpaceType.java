package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;

/**
 * The GreenSpaceType enum represents different types of green spaces.
 * Each enum constant has a specific string representation.
 */
public enum GreenSpaceType implements Serializable {
    /**
     * Represents a garden type of green space.
     */
    GARDEN {
        @Override
        public String toString() {
            return "Garden";
        }
    },
    /**
     * Represents a medium-sized park type of green space.
     */
    MEDIUM_SIZED_PARK {
        @Override
        public String toString() {
            return "Medium-Sized Park";
        }
    },
    /**
     * Represents a large-sized park type of green space.
     */
    LARGE_SIZED_PARK {
        @Override
        public String toString() {
            return "Large-Sized Park";
        }
    },
}

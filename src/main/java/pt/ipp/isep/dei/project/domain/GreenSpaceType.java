package pt.ipp.isep.dei.project.domain;

public enum GreenSpaceType {
    GARDEN {
        public String toString() {
            return "Garden";
        }
    },
    MEDIUM_SIZED_PARK {
        public String toString() {
            return "Medium-Sized Park";
        }
    },
    LARGE_SIZED_PARK {
        public String toString() {
            return "Large-Sized Park";
        }
    },
}

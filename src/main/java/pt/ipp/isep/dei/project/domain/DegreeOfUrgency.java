package pt.ipp.isep.dei.project.domain;

public enum DegreeOfUrgency {

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
    },
}

package pt.ipp.isep.dei.project.domain;

/**
 * Enum representing different types of vehicles.
 */
public enum VehicleType {
    CAR {
        public String toString() {
            return "Car";
        }
    },
    TRUCK {
        public String toString() {
            return "Truck";
        }
    },
    VAN {
        public String toString() {
            return "Van";
        }
    },
    MOTORCYCLE {
        public String toString() {
            return "Motorcycle";
        }
    },
    BICYCLE {
        public String toString() {
            return "Bicycle";
        }
    },
    BUS {
        public String toString() {
            return "Bus";
        }
    },
    SUV {
        public String toString() {
            return "SUV";
        }
    },
    PICKUP_TRUCK {
        public String toString() {
            return "Pickup Truck";
        }
    },
    TRACTOR {
        public String toString() {
            return "Tractor";
        }
    },
    ATV {
        public String toString() {
            return "ATV (All-Terrain Vehicle)";
        }
    },
    GARDEN_TRACTOR {
        public String toString() {
            return "Garden Tractor";
        }
    },
    LAWN_MOWER {
        public String toString() {
            return "Lawn Mower";
        }
    },
    UTILITY_VEHICLE {
        public String toString() {
            return "Utility Vehicle";
        }
    }
}

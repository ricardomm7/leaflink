package pt.ipp.isep.dei.project.domain;

/**
 * Enum representing different types of vehicles.
 */
public enum VehicleType {
    CAR {
        @Override
        public String toString() {
            return "Car";
        }
    },
    TRUCK {
        @Override
        public String toString() {
            return "Truck";
        }
    },
    VAN {
        @Override
        public String toString() {
            return "Van";
        }
    },
    MOTORCYCLE {
        @Override
        public String toString() {
            return "Motorcycle";
        }
    },
    BICYCLE {
        @Override
        public String toString() {
            return "Bicycle";
        }
    },
    BUS {
        @Override
        public String toString() {
            return "Bus";
        }
    },
    SUV {
        @Override
        public String toString() {
            return "SUV";
        }
    },
    PICKUP_TRUCK {
        @Override
        public String toString() {
            return "Pickup Truck";
        }
    };
}

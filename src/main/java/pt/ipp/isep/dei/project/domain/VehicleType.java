/**
 * Enum representing different types of vehicles.
 */
package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;

public enum VehicleType implements Serializable {
    /**
     * Represents a car vehicle type.
     */
    CAR {
        public String toString() {
            return "Car";
        }
    },
    /**
     * Represents a truck vehicle type.
     */
    TRUCK {
        public String toString() {
            return "Truck";
        }
    },
    /**
     * Represents a van vehicle type.
     */
    VAN {
        public String toString() {
            return "Van";
        }
    },
    /**
     * Represents a motorcycle vehicle type.
     */
    MOTORCYCLE {
        public String toString() {
            return "Motorcycle";
        }
    },
    /**
     * Represents a bicycle vehicle type.
     */
    BICYCLE {
        public String toString() {
            return "Bicycle";
        }
    },
    /**
     * Represents a bus vehicle type.
     */
    BUS {
        public String toString() {
            return "Bus";
        }
    },
    /**
     * Represents a SUV (Sport Utility Vehicle) vehicle type.
     */
    SUV {
        public String toString() {
            return "SUV";
        }
    },
    /**
     * Represents a pickup truck vehicle type.
     */
    PICKUP_TRUCK {
        public String toString() {
            return "Pickup Truck";
        }
    },
    /**
     * Represents a tractor vehicle type.
     */
    TRACTOR {
        public String toString() {
            return "Tractor";
        }
    },
    /**
     * Represents an ATV (All-Terrain Vehicle) vehicle type.
     */
    ATV {
        public String toString() {
            return "ATV (All-Terrain Vehicle)";
        }
    },
    /**
     * Represents a garden tractor vehicle type.
     */
    GARDEN_TRACTOR {
        public String toString() {
            return "Garden Tractor";
        }
    },
    /**
     * Represents a lawn mower vehicle type.
     */
    LAWN_MOWER {
        public String toString() {
            return "Lawn Mower";
        }
    },
    /**
     * Represents a utility vehicle type.
     */
    UTILITY_VEHICLE {
        public String toString() {
            return "Utility Vehicle";
        }
    }
}

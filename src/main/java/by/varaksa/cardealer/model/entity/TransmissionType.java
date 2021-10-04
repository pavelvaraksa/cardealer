package by.varaksa.cardealer.model.entity;

/**
 * Enum with different versions of a transmission
 */
public enum TransmissionType {
    AUTOMATIC("transmission.type.automatic"),
    MECHANIC("transmission.type.mechanic"),
    ROBOTIC("transmission.type.robotic");

    public static String transmissionPropertyType;

    TransmissionType(String transmissionPropertyType) {
    }

    public static String getTransmissionPropertyType() {
        return transmissionPropertyType;
    }
}

package by.varaksa.cardealer.model.entity;

public enum FuelType {
    PETROL("fuel.type.petrol"),
    DIESEL("fuel.type.diesel");

    public static String fuelPropertyType;

    FuelType(String fuelPropertyType) {
    }

    public static String getFuelPropertyType() {
        return fuelPropertyType;
    }
}

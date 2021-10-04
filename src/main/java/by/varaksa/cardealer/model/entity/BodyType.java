package by.varaksa.cardealer.model.entity;

/**
 * Enum with different versions of a body
 */
public enum BodyType {
    SEDAN("body.type.sedan"),
    COUPE("body.type.coupe"),
    TOURING("body.type.touring"),
    HATCHBACK("body.type.hatchback"),
    SUV("body.type.suv");

    public static String bodyPropertyType;

    BodyType(String bodyPropertyType) {
    }

    public static String getBodyPropertyType() {
        return bodyPropertyType;
    }
}

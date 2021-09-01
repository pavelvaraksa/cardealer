package by.varaksa.cardealer.model.entity;

public enum City {
    MINSK("city.minsk"),
    BREST("city.brest"),
    MOGILEV("city.mogilev"),
    GOMEL("city.gomel"),
    VITEBSK("city.vitebsk"),
    GRODNO("city.grodno");

    public static String dealerPropertyCity;

    City(String dealerPropertyCity) {
    }

    public static String getDealerPropertyCity() {
        return dealerPropertyCity;
    }
}


package by.varaksa.cardealer.model.entity;

public enum Country {
    GERMANY("country.germany"),
    POLAND("country.poland"),
    CZECH("country.czech");

    public static String countryPropertyName;

    Country(String countryPropertyName) {
    }

    public static String getCountryPropertyName() {
        return countryPropertyName;
    }
}

package by.varaksa.cardealer.model.entity;

/**
 * Enum with different versions of a country
 */
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

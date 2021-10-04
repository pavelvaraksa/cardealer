package by.varaksa.cardealer.model.entity;

/**
 * Enum with different versions of a color
 */
public enum Color {
    BLACK("color.black"),
    WHITE("color.white"),
    RED("color.red"),
    GREEN("color.green"),
    BLUE("color.blue"),
    YELLOW("color.yellow"),
    BROWN("color.brown"),
    SILVER("color.silver"),
    ORANGE("color.orange"),
    GREY("color.grey"),
    CHAMPAGNE("color.champagne");

    public static String bodyPropertyColor;

    Color(String bodyPropertyColor) {
    }

    public static String getBodyPropertyColor() {
        return bodyPropertyColor;
    }
}

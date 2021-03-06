package by.varaksa.cardealer.controller.command;

/**
 * Enum with different address lines
 */
public enum Commands {
    //user
    LOGOUT("/logout"),
    SAVE_USER("/user/save"),
    FIND_ALL_USERS("/user/find-all"),
    FIND_USER_BY_LOGIN("/user-info/find-user"),
    FIND_USER_BY_ID("/user/find"),
    UPDATE_USER("/user/update"),
    UPDATE_USER_BY_LOGIN("/user-info/update-user"),
    VERIFY_USER("/register/verify"),

    //car
    SAVE_CAR("/car/save"),
    FIND_ALL_CARS("/car/find-all"),
    FIND_ALL_CARS_FOR_USER("/car-for-user/find-all-for-user"),
    UPDATE_CAR("/car/update"),
    DELETE_CAR("/car/delete"),

    //body
    SAVE_BODY("/body/save"),
    FIND_ALL_BODIES("/body/find-all"),
    UPDATE_BODY("/body/update"),
    DELETE_BODY("/body/delete"),

    //dealer
    SAVE_DEALER("/dealer/save"),
    FIND_ALL_DEALERS("/dealer/find-all"),
    UPDATE_DEALER("/dealer/update"),
    DELETE_DEALER("/dealer/delete"),

    //engine
    SAVE_ENGINE("/engine/save"),
    FIND_ALL_ENGINES("/engine/find-all"),
    UPDATE_ENGINE("/engine/update"),
    DELETE_ENGINE("/engine/delete"),

    //transmission
    SAVE_TRANSMISSION("/transmission/save"),
    FIND_ALL_TRANSMISSIONS("/transmission/find-all"),
    UPDATE_TRANSMISSION("/transmission/update"),
    DELETE_TRANSMISSION("/transmission/delete"),

    //user order
    SAVE_USER_ORDER("/user-order/save"),
    SAVE_USER_ORDER_FOR_USER("/user-order-for-user/save-for-user"),
    FIND_ALL_USER_ORDERS("/user-order/find-all"),
    FIND_ALL_USER_ORDERS_FOR_USER("/user-order-for-user/find-all-for-user"),
    UPDATE_USER_ORDER("/user-order/update"),
    DELETE_USER_ORDER("/user-order/delete"),

    //home page
    DEFAULT("/");

    private final String commandName;

    Commands(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    /**
     * Find command by string name
     *
     * @param commandName {@code String} command name
     * @return {@code Commands} action
     */
    public static Commands findByCommandName(String commandName) {

        if (commandName != null) {
            for (Commands value : Commands.values()) {
                if (value.getCommandName().equalsIgnoreCase(commandName)) {
                    return value;
                }
            }
        }
        return DEFAULT;
    }
}

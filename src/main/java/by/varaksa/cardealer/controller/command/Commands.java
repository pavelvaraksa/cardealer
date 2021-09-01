package by.varaksa.cardealer.controller.command;

public enum Commands {
    LOGOUT("/logout"),
    SAVE_USER("/user/save"),
    FIND_ALL_USERS("/user/find-all"),
    FIND_USER_BY_ID("/user/find-by-id"),
    UPDATE_USER("/user/update"),
    DELETE_USER("/user/delete"),
    VERIFY_USER("/register/verify"),

    SAVE_CAR("/car/save"),
    FIND_ALL_CARS("/car/find-all"),
    FIND_CAR_BY_ID("/car/find-by-id"),
    UPDATE_CAR("/car/update"),
    DELETE_CAR("/car/delete"),

    SAVE_BODY("/body/save"),
    FIND_ALL_BODIES("/body/find-all"),
    FIND_BODY_BY_ID("/body/find-by-id"),
    UPDATE_BODY("/body/update"),
    DELETE_BODY("/body/delete"),

    SAVE_DEALER("/dealer/save"),
    FIND_ALL_DEALERS("/dealer/find-all"),
    FIND_DEALER_BY_ID("/dealer/find-by-id"),
    UPDATE_DEALER("/dealer/update"),
    DELETE_DEALER("/dealer/delete"),

    SAVE_ENGINE("/engine/save"),
    FIND_ALL_ENGINES("/engine/find-all"),
    FIND_ENGINE_BY_ID("/engine/find-by-id"),
    UPDATE_ENGINE("/engine/update"),
    DELETE_ENGINE("/engine/delete"),

    SAVE_TRANSMISSION("/transmission/save"),
    FIND_ALL_TRANSMISSIONS("/transmission/find-all"),
    FIND_TRANSMISSION_BY_ID("/transmission/find-by-id"),
    UPDATE_TRANSMISSION("/transmission/update"),
    DELETE_TRANSMISSION("/transmission/delete"),

    SAVE_USER_ORDER("/user-order/save"),
    FIND_ALL_USER_ORDERS("/user-order/find-all"),
    FIND_USER_ORDER_BY_ID("/user-order/find-by-id"),
    UPDATE_USER_ORDER("/user-order/update"),
    DELETE_USER_ORDER("/user-order/delete"),

    DEFAULT("/");

    private final String commandName;

    Commands(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

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

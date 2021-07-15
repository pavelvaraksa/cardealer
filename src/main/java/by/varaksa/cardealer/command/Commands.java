package by.varaksa.cardealer.command;

public enum Commands {
    LOGIN("/login"),
    LOGOUT("/logout"),
    SAVE("/save"),
    FIND_ALL("/main-menu/find-all"),
    FIND_BY_ID("/main-menu/find-by-id"),
    UPDATE("/main-menu/update"),
    DELETE("/main-menu/delete"),
    DEFAULT("/main-menu/find-all");

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

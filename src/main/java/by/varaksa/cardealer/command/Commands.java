package by.varaksa.cardealer.command;

public enum Commands {
    LOGIN("/login"),
    LOGOUT("/logout"),
    SAVE("/save"),
    FIND_ALL("/find-all"),
    FIND_BY_ID("/find-by-id"),
    UPDATE("/update"),
    DELETE("/delete"),
    DEFAULT("/find-all");

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

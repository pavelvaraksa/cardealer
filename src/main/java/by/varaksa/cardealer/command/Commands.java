package by.varaksa.cardealer.command;

public enum Commands {
    SHOW("show"),
    CREATE("create"),
    FIND_ALL("findAll"),
    FIND_BY_ID("findById"),
    UPDATE("update"),
    DELETE("delete"),
    DEFAULT("findAll");

    private String commandName;

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

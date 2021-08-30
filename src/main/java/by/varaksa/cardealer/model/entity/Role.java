package by.varaksa.cardealer.model.entity;

public enum Role {
    GUEST("role.guest"),
    USER("role.user"),
    ADMIN("role.admin");

    public static String rolePropertyName;

    Role(String rolePropertyName) {
    }

    public static String getRolePropertyName() {
        return rolePropertyName;
    }
}

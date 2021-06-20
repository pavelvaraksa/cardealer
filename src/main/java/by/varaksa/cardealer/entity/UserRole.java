package by.varaksa.cardealer.entity;

public class UserRole {
    private Long id;

    private Role roleName;

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }

    @Override
    public int hashCode() {
        return (int) (11 * id + roleName.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        UserRole userRole = (UserRole) object;

        return id == userRole.id
                && (roleName == userRole.roleName || roleName != null && roleName.equals(userRole.getRoleName()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User role: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("role name ").append(roleName);
        return stringBuilder.toString();
    }
}

package by.varaksa.cardealer.model.entity;

public class UserRole {
    private Long id;

    private Role roleName;

    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
                && (roleName == userRole.roleName || roleName != null && roleName.equals(userRole.getRoleName()))
                && (userId == userRole.userId || userId != null && userId.equals(userRole.getUserId()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User role: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("role name ").append(roleName).append(", ");
        stringBuilder.append("user id ").append(userId);
        return stringBuilder.toString();
    }
}

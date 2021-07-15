package by.varaksa.cardealer.entity;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class User {
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String login;

    private String password;

    private Role role;

    private boolean isBlocked;

    private LocalDateTime created;

    private LocalDateTime changed;

    public User() {
    }

    public User(String firstName, String lastName, LocalDate birthDate, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
    }

    public User(Long id, String firstName, String lastname, LocalDate birthDate, String login,
                Role role, boolean isBlocked, LocalDateTime created, LocalDateTime changed) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastname;
        this.birthDate = birthDate;
        this.login = login;
        this.role = role;
        this.isBlocked = isBlocked;
        this.created = created;
        this.changed = changed;
    }

    public User(Long id, String firstName, String lastname, LocalDate birthDate, String login, String password,
                Role role, boolean isBlocked, LocalDateTime created, LocalDateTime changed) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastname;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
        this.created = created;
        this.changed = changed;
    }

    public User(String firstname, String lastName, LocalDate birthDate, String login, String password,
                Role role, boolean isBlocked, Long id) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getChanged() {
        return changed;
    }

    public void setChanged(LocalDateTime changed) {
        this.changed = changed;
    }

    @Override
    public int hashCode() {
        return (int) (11 * id
                + firstName.hashCode()
                + lastName.hashCode()
                + birthDate.hashCode()
                + login.hashCode()
                + password.hashCode()
                + role.hashCode()
                + created.hashCode()
                + changed.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        User user = (User) object;

        return id == user.id
                && (firstName == user.firstName || firstName != null && firstName.equals(user.getFirstName()))
                && (lastName == user.lastName || lastName != null && lastName.equals(user.getLastName()))
                && (birthDate == user.birthDate || birthDate != null && birthDate.equals(user.getBirthDate()))
                && (login == user.login || login != null && login.equals(user.getLogin()))
                && (password == user.password || password != null && password.equals(user.getPassword()))
                && (role == user.role || role != null && role.equals(user.getRole()))
                && (created == user.created || created != null && created.equals(user.getCreated()))
                && (changed == user.changed || changed != null && changed.equals(user.getChanged()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("firstname ").append(firstName).append(", ");
        stringBuilder.append("lastname ").append(lastName).append(", ");
        stringBuilder.append("birth date ").append(birthDate).append(", ");
        stringBuilder.append("login ").append(login).append(", ");
        stringBuilder.append("password ").append(password).append(", ");
        stringBuilder.append("role ").append(role).append(", ");
        stringBuilder.append("is blocked ").append(isBlocked).append(", ");
        stringBuilder.append("created ").append(created).append(", ");
        stringBuilder.append("changed ").append(changed).append(", ");
        return stringBuilder.toString();
    }
}

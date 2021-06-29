package by.varaksa.cardealer.entity;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class User {
    private Long id;

    private String name;

    private String surname;

    private LocalDate birthDate;

    private String login;

    private String password;

    private Role role;

    private boolean isBlocked;

    private LocalDateTime created;

    private LocalDateTime changed;

    public User() {
    }

    public User(String name, String surname, LocalDate birthDate, String login, String password,
                Role role) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
                + name.hashCode()
                + surname.hashCode()
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
                && (name == user.name || name != null && name.equals(user.getName()))
                && (surname == user.surname || surname != null && surname.equals(user.getSurname()))
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
        stringBuilder.append("name ").append(name).append(", ");
        stringBuilder.append("surname ").append(surname).append(", ");
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

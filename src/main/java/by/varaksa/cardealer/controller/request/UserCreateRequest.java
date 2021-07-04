package by.varaksa.cardealer.controller.request;

import by.varaksa.cardealer.entity.Role;

import java.util.Date;

public class UserCreateRequest {
    private Long id;

    private String name;

    private String surname;

    private Date birthDate;

    private String login;

    private String password;

    private Role role;

    private boolean isBlocked;

    public UserCreateRequest() {
    }

    public UserCreateRequest(Long id,
                             String name,
                             String surname,
                             Date birthDate,
                             String login,
                             String password,
                             Role role,
                             boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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

    @Override
    public int hashCode() {
        return (int) (11 * id
                + name.hashCode()
                + surname.hashCode()
                + birthDate.hashCode()
                + login.hashCode()
                + password.hashCode()
                + role.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        UserCreateRequest user = (UserCreateRequest) object;

        return id == user.id
                && (name == user.name || name != null && name.equals(user.getName()))
                && (surname == user.surname || surname != null && surname.equals(user.getSurname()))
                && (birthDate == user.birthDate || birthDate != null && birthDate.equals(user.getBirthDate()))
                && (login == user.login || login != null && login.equals(user.getLogin()))
                && (password == user.password || password != null && password.equals(user.getPassword()))
                && (role == user.role || role != null && role.equals(user.getRole()));
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
        stringBuilder.append("is blocked ").append(isBlocked);

        return stringBuilder.toString();
    }
}

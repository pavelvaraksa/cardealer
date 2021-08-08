package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class User {
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String login;

    private String password;

    private String email;

    private String codeToRegister;

    private Role role;

    private boolean isBlocked;

    private LocalDateTime created;

    private LocalDateTime changed;

    public User() {
    }

    public User(String firstname, String lastName, LocalDate birthDate, String email, Role role, boolean isBlocked) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    public User(String firstName, String lastName, LocalDate birthDate, String login, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(String firstName, String lastName,
                LocalDate birthDate, String login, String password,
                String email, String codeToRegister) {
        this(firstName, lastName, birthDate, login, password, email);
        this.codeToRegister = codeToRegister;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodeToRegister() {
        return codeToRegister;
    }

    public void setCodeToRegister(String codeToRegister) {
        this.codeToRegister = codeToRegister;
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
                + email.hashCode()
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
                && (email == user.email || email != null && email.equals(user.getEmail()))
                && (role == user.role || role != null && role.equals(user.getRole()))
                && (created == user.created || created != null && created.equals(user.getCreated()))
                && (changed == user.changed || changed != null && changed.equals(user.getChanged()));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", isBlocked=" + isBlocked +
                ", created=" + created +
                ", changed=" + changed +
                '}';
    }
}

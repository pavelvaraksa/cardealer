package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class {@code User} designed to store data about a user
 *
 * @author Pavel Varaksa
 */
public class User {
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String phoneNumber;

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

    public User(String firstname, String lastName, LocalDate birthDate, String phoneNumber, Role role, boolean isBlocked) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    public User(String firstName, String lastName, LocalDate birthDate, String phoneNumber, String login, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(String firstName, String lastName, LocalDate birthDate, String phoneNumber, String login, String password, String email, String codeToRegister) {
        this(firstName, lastName, birthDate, phoneNumber, login, password, email);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}

package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;
import java.time.LocalDate;

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

    @Override
    public int hashCode() {
        int result = id == null ? 0 : id.hashCode();
        result = 11 * result + (firstName == null ? 0 : firstName.hashCode());
        result = 11 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 11 * result + (birthDate == null ? 0 : birthDate.hashCode());
        result = 11 * result + (phoneNumber == null ? 0 : phoneNumber.hashCode());
        result = 11 * result + (login == null ? 0 : login.hashCode());
        result = 11 * result + (password == null ? 0 : password.hashCode());
        result = 11 * result + (email == null ? 0 : email.hashCode());
        result = 11 * result + (role == null ? 0 : role.hashCode());
        result = 11 * result + (created == null ? 0 : created.hashCode());
        result = 11 * result + (changed == null ? 0 : changed.hashCode());
        return result;
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

        if (id != null ? !id.equals(user.id) : user.id == null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (created != null ? !created.equals(user.created) : user.created != null) return false;
        if (changed != null ? !changed.equals(user.changed) : user.changed != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "User: " +
                "id = " + id +
                ", first name is " + firstName +
                ", last name is " + lastName +
                ", birth date = " + birthDate +
                ", phone number = " + phoneNumber +
                ", login is " + login +
                ", password is " + password +
                ", email is " + email +
                ", role is " + role +
                ", blocked is " + isBlocked +
                ", created on " + created +
                ", changed on " + changed;
    }
}

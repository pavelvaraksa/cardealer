package by.varaksa.cardealer.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class {@code Dealer} designed to store data about a dealer
 *
 * @author Pavel Varaksa
 */
public class Dealer {
    private Long id;

    private String name;

    private String address;

    private LocalDate foundationDate;

    private City city;

    private LocalDateTime created;

    private LocalDateTime changed;

    public Dealer() {
    }

    public Dealer(String name, String address, LocalDate foundationDate, City city) {
        this.name = name;
        this.address = address;
        this.foundationDate = foundationDate;
        this.city = city;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
        result = 11 * result + (name == null ? 0 : name.hashCode());
        result = 11 * result + (address == null ? 0 : address.hashCode());
        result = 11 * result + (foundationDate == null ? 0 : foundationDate.hashCode());
        result = 11 * result + (city == null ? 0 : city.hashCode());
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

        Dealer dealer = (Dealer) object;

        return id == dealer.id &&
                name == dealer.name &&
                address == dealer.address &&
                foundationDate == dealer.foundationDate &&
                city == dealer.city &&
                created == dealer.created &&
                changed == dealer.changed;
    }

    @Override
    public String toString() {
        return "Dealer: " +
                "id = " + id +
                ", name is " + name +
                ", address is " + address +
                ", foundation date = " + foundationDate +
                ", city is " + city +
                ", created on " + created +
                ", changed on " + changed;
    }
}

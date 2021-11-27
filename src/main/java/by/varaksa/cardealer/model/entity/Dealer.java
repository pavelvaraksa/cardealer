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
}

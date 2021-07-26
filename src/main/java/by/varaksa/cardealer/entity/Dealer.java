package by.varaksa.cardealer.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Dealer {
    private Long id;

    private String name;

    private String address;

    private LocalDate foundationDate;

    private City city;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long carId;

    public Dealer() {
    }

    public Dealer(String name,
                  String address,
                  LocalDate foundationDate,
                  City city,
                  Long carId) {
        this.name = name;
        this.address = address;
        this.foundationDate = foundationDate;
        this.city = city;
        this.carId = carId;
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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Override
    public int hashCode() {
        return (int) (11 * id
                + name.hashCode()
                + address.hashCode()
                + foundationDate.hashCode()
                + city.hashCode()
                + created.hashCode()
                + changed.hashCode()
                + carId.hashCode());
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

        return id == dealer.id
                && (name == dealer.name || name != null && name.equals(dealer.getName()))
                && (address == dealer.address || address != null && address.equals(dealer.getAddress()))
                && (foundationDate == dealer.foundationDate || foundationDate != null && foundationDate.equals(dealer.getFoundationDate()))
                && (city == dealer.city || city != null && city.equals(dealer.getCity()))
                && (created == dealer.created || created != null && created.equals(dealer.getCreated()))
                && (changed == dealer.changed || changed != null && changed.equals(dealer.getChanged()))
                && (carId == dealer.carId || carId != null && carId.equals(dealer.getCarId()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dealer: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("name ").append(name).append(", ");
        stringBuilder.append("address ").append(address).append(", ");
        stringBuilder.append("foundation date ").append(foundationDate).append(", ");
        stringBuilder.append("city ").append(city).append(", ");
        stringBuilder.append("created ").append(created).append(", ");
        stringBuilder.append("changed ").append(changed).append(", ");
        stringBuilder.append("car id ").append(carId);
        return stringBuilder.toString();
    }
}

package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;

/**
 * Class {@code UserOrder} designed to store data about a user order
 *
 * @author Pavel Varaksa
 */
public class UserOrder {
    private Long id;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long userId;

    private Long carId;

    private Model model;

    private Integer price;

    private String name;

    private FuelType fuelType;

    public UserOrder() {
    }

    public UserOrder(Long userId, Long carId) {
        this.userId = userId;
        this.carId = carId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public int hashCode() {
        int result = id == null ? 0 : id.hashCode();
        result = 11 * result + (created == null ? 0 : created.hashCode());
        result = 11 * result + (changed == null ? 0 : changed.hashCode());
        result = 11 * result + (userId == null ? 0 : userId.hashCode());
        result = 11 * result + (carId == null ? 0 : carId.hashCode());
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

        UserOrder userOrder = (UserOrder) object;

        if (id != null ? !id.equals(userOrder.id) : userOrder.id != null) return false;
        if (created != null ? !created.equals(userOrder.created) : userOrder.created != null) return false;
        if (changed != null ? !changed.equals(userOrder.changed) : userOrder.changed != null) return false;
        if (userId != null ? !userId.equals(userOrder.userId) : userOrder.userId != null) return false;
        if (carId != null ? !carId.equals(userOrder.carId) : userOrder.carId != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "User order: " +
                "id = " + id +
                ", created on " + created +
                ", changed on " + changed +
                ", user id = " + userId +
                ", car id = " + carId;
    }
}

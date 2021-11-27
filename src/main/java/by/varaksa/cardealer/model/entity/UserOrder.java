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
}

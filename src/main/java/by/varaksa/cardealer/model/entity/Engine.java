package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;

/**
 * Class {@code Engine} designed to store data about an engine
 *
 * @author Pavel Varaksa
 */
public class Engine {
    private Long id;

    private FuelType fuelType;

    private Double volume;

    private Integer cylindersCount;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long carId;

    public Engine() {
    }

    public Engine(FuelType fuelType, Double volume, Integer cylindersCount, Long carId) {
        this.fuelType = fuelType;
        this.volume = volume;
        this.cylindersCount = cylindersCount;
        this.carId = carId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Integer getCylindersCount() {
        return cylindersCount;
    }

    public void setCylindersCount(Integer cylindersCount) {
        this.cylindersCount = cylindersCount;
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
}

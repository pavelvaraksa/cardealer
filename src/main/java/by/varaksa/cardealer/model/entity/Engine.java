package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;

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

    public Engine(FuelType fuelType,
                  Double volume,
                  Integer cylindersCount,
                  Long carId) {
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

    @Override
    public int hashCode() {
        return (int) (11 * id
                + fuelType.hashCode()
                + volume.hashCode()
                + cylindersCount.hashCode()
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

        Engine engine = (Engine) object;

        return id == engine.id
                && (fuelType == engine.fuelType || fuelType != null && fuelType.equals(engine.getFuelType()))
                && (volume == engine.volume || volume != null && volume.equals(engine.getVolume()))
                && (cylindersCount == engine.cylindersCount || cylindersCount != null && cylindersCount.equals(engine.getCylindersCount()))
                && (created == engine.created || created != null && created.equals(engine.getCreated()))
                && (changed == engine.changed || changed != null && changed.equals(engine.getChanged()))
                && (carId == engine.carId || carId != null && carId.equals(engine.getCarId()));
    }

    @Override
    public String toString() {
        return "Engine: " +
                "id = " + id +
                ", fuel type is " + fuelType +
                ", volume = " + volume +
                ", cylinders count = " + cylindersCount +
                ", created on " + created +
                ", changed on " + changed +
                ", car id = " + carId;
    }
}

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
        int result = id == null ? 0 : id.hashCode();
        result = 11 * result + (fuelType == null ? 0 : fuelType.hashCode());
        result = 11 * result + (volume == null ? 0 : volume.hashCode());
        result = 11 * result + (cylindersCount == null ? 0 : cylindersCount.hashCode());
        result = 11 * result + (created == null ? 0 : created.hashCode());
        result = 11 * result + (changed == null ? 0 : changed.hashCode());
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

        Engine engine = (Engine) object;

        if (id != null ? !id.equals(engine.id) : engine.id != null) return false;
        if (fuelType != null ? !fuelType.equals(engine.fuelType) : engine.fuelType != null) return false;
        if (volume != null ? !volume.equals(engine.volume) : engine.volume != null) return false;
        if (cylindersCount != null ? !cylindersCount.equals(engine.cylindersCount) : engine.cylindersCount != null) return false;
        if (created != null ? !created.equals(engine.created) : engine.created != null) return false;
        if (changed != null ? !changed.equals(engine.changed) : engine.changed != null) return false;
        if (carId != null ? !carId.equals(engine.carId) : engine.carId != null) return false;

        return true;
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

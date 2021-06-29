package by.varaksa.cardealer.entity;

import java.time.LocalDateTime;

public class Engine {
    private Long id;

    private EngineType engineType;

    private FuelType fuelType;

    private Double volume;

    private Integer cylindersCount;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long carId;

    public Engine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
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
                + engineType.hashCode()
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
                && (engineType == engine.engineType || engineType != null && engineType.equals(engine.getEngineType()))
                && (fuelType == engine.fuelType || fuelType != null && fuelType.equals(engine.getFuelType()))
                && (volume == engine.volume || volume != null && volume.equals(engine.getVolume()))
                && (cylindersCount == engine.cylindersCount || cylindersCount != null && cylindersCount.equals(engine.getCylindersCount()))
                && (created == engine.created || created != null && created.equals(engine.getCreated()))
                && (changed == engine.changed || changed != null && changed.equals(engine.getChanged()))
                && (carId == engine.carId || carId != null && carId.equals(engine.getCarId()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Engine: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("engine type ").append(engineType).append(", ");
        stringBuilder.append("fuel type ").append(fuelType).append(", ");
        stringBuilder.append("volume ").append(volume).append(", ");
        stringBuilder.append("cylinders count ").append(cylindersCount).append(", ");
        stringBuilder.append("created ").append(created).append(", ");
        stringBuilder.append("changed ").append(changed).append(", ");
        stringBuilder.append("car id ").append(carId);
        return stringBuilder.toString();
    }
}

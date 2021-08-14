package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;

public class Transmission {
    private Long id;

    private TransmissionType transmissionType;

    private Integer gearsCount;

    private Integer weight;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long carId;

    public Transmission() {
    }

    public Transmission(TransmissionType transmissionType, Integer gearsCount, Integer weight, Long carId) {
        this.transmissionType = transmissionType;
        this.gearsCount = gearsCount;
        this.weight = weight;
        this.carId = carId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Integer getGearsCount() {
        return gearsCount;
    }

    public void setGearsCount(Integer gearsCount) {
        this.gearsCount = gearsCount;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
                + transmissionType.hashCode()
                + gearsCount.hashCode()
                + weight.hashCode()
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

        Transmission transmission = (Transmission) object;

        return id == transmission.id
                && (transmissionType == transmission.transmissionType || transmissionType != null && transmissionType.equals(transmission.getTransmissionType()))
                && (gearsCount == transmission.gearsCount || gearsCount != null && gearsCount.equals(transmission.getGearsCount()))
                && (weight == transmission.weight || weight != null && weight.equals(transmission.getWeight()))
                && (created == transmission.created || created != null && created.equals(transmission.getCreated()))
                && (changed == transmission.changed || changed != null && changed.equals(transmission.getChanged()))
                && (carId == transmission.carId || carId != null && carId.equals(transmission.getCarId()));
    }

    @Override
    public String toString() {
        return "Transmission: " +
                "id = " + id +
                ", transmission type is " + transmissionType +
                ", gears count = " + gearsCount +
                ", weight = " + weight +
                ", created on " + created +
                ", changed on " + changed +
                ", car id = " + carId;
    }
}

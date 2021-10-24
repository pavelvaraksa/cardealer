package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;

/**
 * Class {@code Transmission} designed to store data about a transmission
 *
 * @author Pavel Varaksa
 */
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
        int result = id == null ? 0 : id.hashCode();
        result = 11 * result + (transmissionType == null ? 0 : transmissionType.hashCode());
        result = 11 * result + (gearsCount == null ? 0 : gearsCount.hashCode());
        result = 11 * result + (weight == null ? 0 : weight.hashCode());
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

        Transmission transmission = (Transmission) object;

        return id == transmission.id &&
                transmissionType == transmission.transmissionType &&
                gearsCount == transmission.gearsCount &&
                weight == transmission.weight &&
                created == transmission.created &&
                changed == transmission.changed &&
                carId == transmission.carId;
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

package by.varaksa.cardealer.entity;

import java.time.LocalDateTime;

public class Transmission {
    private Long id;

    private TransmissionType transmissionType;

    private Integer gearsCount;

    private Double weight;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long carId;

    public Transmission() {
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
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
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Transmission: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("transmission type ").append(transmissionType).append(", ");
        stringBuilder.append("gears count ").append(gearsCount).append(", ");
        stringBuilder.append("weight ").append(weight).append(", ");
        stringBuilder.append("created ").append(created).append(", ");
        stringBuilder.append("changed ").append(changed).append(", ");
        stringBuilder.append("car id ").append(carId);
        return stringBuilder.toString();
    }
}

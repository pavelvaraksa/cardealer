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
}

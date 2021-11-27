package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;

/**
 * Class {@code Body} designed to store data about a body
 *
 * @author Pavel Varaksa
 */
public class Body {
    private Long id;

    private Color color;

    private BodyType bodyType;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long carId;

    public Body() {
    }

    public Body(Color color, BodyType bodyType) {
        this.color = color;
        this.bodyType = bodyType;
    }

    public Body(Color color, BodyType bodyType, Long carId) {
        this.color = color;
        this.bodyType = bodyType;
        this.carId = carId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
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



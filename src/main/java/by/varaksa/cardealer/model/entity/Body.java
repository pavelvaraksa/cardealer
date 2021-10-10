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

    @Override
    public int hashCode() {
        return (int) (11 * id
                + color.hashCode()
                + bodyType.hashCode()
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

        Body body = (Body) object;

        return id == body.id
                && (color == body.color || color != null && color.equals(body.getColor()))
                && (bodyType == body.bodyType || bodyType != null && bodyType.equals(body.getBodyType()))
                && (created == body.created || created != null && created.equals(body.getCreated()))
                && (changed == body.changed || changed != null && changed.equals(body.getChanged()))
                && (carId == body.carId || carId != null && carId.equals(body.getCarId()));
    }

    @Override
    public String toString() {
        return "Body: " +
                "id = " + id +
                ", color is " + color +
                ", body type is " + bodyType +
                ", created on " + created +
                ", changed on " + changed +
                ", car id = " + carId;
    }
}



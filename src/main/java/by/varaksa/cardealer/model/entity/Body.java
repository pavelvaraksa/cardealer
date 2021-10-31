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
        int result = id == null ? 0 : id.hashCode();
        result = 11 * result + (color == null ? 0 : color.hashCode());
        result = 11 * result + (bodyType == null ? 0 : bodyType.hashCode());
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

        Body body = (Body) object;

        if (id != null ? !id.equals(body.id) : body.id != null) return false;
        if (color != null ? !color.equals(body.color) : body.color != null) return false;
        if (bodyType != null ? !bodyType.equals(body.bodyType) : body.bodyType != null) return false;
        if (created != null ? !created.equals(body.created) : body.created != null) return false;
        if (changed != null ? !changed.equals(body.changed) : body.changed != null) return false;
        if (carId != null ? !carId.equals(body.carId) : body.carId != null) return false;

        return true;
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



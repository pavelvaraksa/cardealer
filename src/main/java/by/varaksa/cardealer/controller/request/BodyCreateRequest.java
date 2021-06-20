package by.varaksa.cardealer.controller.request;

import by.varaksa.cardealer.entity.BodyType;
import by.varaksa.cardealer.entity.Color;

import java.util.Date;

public class BodyCreateRequest {
    private Long id;

    private Color color;

    private BodyType bodyType;

    private String vin;

    private Date created;

    private Date changed;

    private Long carId;

    public BodyCreateRequest() {
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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
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
                + vin.hashCode()
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

        BodyCreateRequest body = (BodyCreateRequest) object;

        return id == body.id
                && (color == body.color || color != null && color.equals(body.getColor()))
                && (bodyType == body.bodyType || bodyType != null && bodyType.equals(body.getBodyType()))
                && (vin == body.vin || vin != null && vin.equals(body.getVin()))
                && (created == body.created || created != null && created.equals(body.getCreated()))
                && (changed == body.changed || changed != null && changed.equals(body.getChanged()))
                && (carId == body.carId || carId != null && carId.equals(body.getCarId()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Body: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("color ").append(color).append(", ");
        stringBuilder.append("body type ").append(bodyType).append(", ");
        stringBuilder.append("vin ").append(vin).append(", ");
        stringBuilder.append("created ").append(created).append(", ");
        stringBuilder.append("changed ").append(changed).append(", ");
        stringBuilder.append("car id ").append(carId);
        return stringBuilder.toString();
    }
}

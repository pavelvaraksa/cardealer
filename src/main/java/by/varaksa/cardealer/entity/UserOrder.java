package by.varaksa.cardealer.entity;

import java.time.LocalDateTime;

public class UserOrder {
    private Long id;

    private String orderName;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long userId;

    public UserOrder() {
    }

    public UserOrder(String orderName, Long userId) {
        this.orderName = orderName;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Override
    public int hashCode() {
        return (int) (11 * id
                + orderName.hashCode()
                + created.hashCode()
                + changed.hashCode()
                + userId.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        UserOrder userOrder = (UserOrder) object;

        return id == userOrder.id
                && (orderName == userOrder.orderName || orderName != null && orderName.equals(userOrder.getOrderName()))
                && (created == userOrder.created || created != null && created.equals(userOrder.getCreated()))
                && (changed == userOrder.changed || changed != null && changed.equals(userOrder.getChanged()))
                && (userId == userOrder.userId || userId != null && userId.equals(userOrder.getUserId()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User order: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("order name").append(orderName).append(", ");
        stringBuilder.append("created ").append(created).append(", ");
        stringBuilder.append("changed ").append(changed).append(", ");
        stringBuilder.append("user id ").append(userId).append(", ");
        return stringBuilder.toString();
    }
}

package by.varaksa.cardealer.controller.request;

import java.util.Date;

public class UserOrderCreateRequest {
    private Long id;

    private Integer count;

    private Date created;

    private Date changed;

    private Long userId;

    private Long orderId;

    public UserOrderCreateRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        return (int) (11 * id
                + count.hashCode()
                + created.hashCode()
                + changed.hashCode()
                + userId.hashCode())
                + orderId.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        UserOrderCreateRequest userOrder = (UserOrderCreateRequest) object;

        return id == userOrder.id
                && (count == userOrder.count || count != null && count.equals(userOrder.getCount()))
                && (created == userOrder.created || created != null && created.equals(userOrder.getCreated()))
                && (changed == userOrder.changed || changed != null && changed.equals(userOrder.getChanged()))
                && (userId == userOrder.userId || userId != null && userId.equals(userOrder.getUserId()))
                && (orderId == userOrder.orderId || orderId != null && orderId.equals(userOrder.getOrderId()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User order: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("count ").append(count).append(", ");
        stringBuilder.append("created ").append(created).append(", ");
        stringBuilder.append("changed ").append(changed).append(", ");
        stringBuilder.append("user id ").append(userId).append(", ");
        stringBuilder.append("order id ").append(orderId);
        return stringBuilder.toString();
    }
}

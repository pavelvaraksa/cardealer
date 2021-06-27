package by.varaksa.cardealer.controller.request;

public class UserOrderCreateRequest {
    private Long id;

    private Integer count;

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
                && (userId == userOrder.userId || userId != null && userId.equals(userOrder.getUserId()))
                && (orderId == userOrder.orderId || orderId != null && orderId.equals(userOrder.getOrderId()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User order: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("count ").append(count).append(", ");
        stringBuilder.append("user id ").append(userId).append(", ");
        stringBuilder.append("order id ").append(orderId);
        return stringBuilder.toString();
    }
}

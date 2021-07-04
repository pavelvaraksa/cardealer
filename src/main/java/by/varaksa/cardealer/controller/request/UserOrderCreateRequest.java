package by.varaksa.cardealer.controller.request;

public class UserOrderCreateRequest {
    private Long id;

    private String orderName;

    private Integer count;

    private Long userId;

    public UserOrderCreateRequest() {
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

    @Override
    public int hashCode() {
        return (int) (11 * id
                + orderName.hashCode()
                + count.hashCode()
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

        UserOrderCreateRequest userOrder = (UserOrderCreateRequest) object;

        return id == userOrder.id
                && (orderName == userOrder.orderName || orderName != null && orderName.equals(userOrder.getOrderName()))
                && (count == userOrder.count || count != null && count.equals(userOrder.getCount()))
                && (userId == userOrder.userId || userId != null && userId.equals(userOrder.getUserId()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User order: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("order name ").append(orderName).append(", ");
        stringBuilder.append("count ").append(count).append(", ");
        stringBuilder.append("user id ").append(userId);
        return stringBuilder.toString();
    }
}

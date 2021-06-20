package by.varaksa.cardealer.controller.request;

public class OrderCreateRequest {
    private Long id;

    private String orderName;

    public OrderCreateRequest() {
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

    @Override
    public int hashCode() {
        return (int) (11 * id + orderName.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        OrderCreateRequest order = (OrderCreateRequest) object;

        return id == order.id
                && (orderName == order.orderName || orderName != null && orderName.equals(order.getOrderName()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("order name ").append(orderName);
        return stringBuilder.toString();
    }
}

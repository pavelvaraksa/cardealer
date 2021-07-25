package by.varaksa.cardealer.entity;

import java.time.LocalDateTime;

public class Car {
    private Long id;

    private Brand brand;

    private Model model;

    private Country issueCountry;

    private Integer guaranteePeriod;

    private Double price;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long userOrderId;

    public Car() {
    }

    public Car(Brand brand,
               Model model,
               Country issueCountry,
               Integer guaranteePeriod,
               Double price) {
        this.brand = brand;
        this.model = model;
        this.issueCountry = issueCountry;
        this.guaranteePeriod = guaranteePeriod;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Country getIssueCountry() {
        return issueCountry;
    }

    public void setIssueCountry(Country issueCountry) {
        this.issueCountry = issueCountry;
    }

    public Integer getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public void setGuaranteePeriod(Integer guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(Long userOrderId) {
        this.userOrderId = userOrderId;
    }

    @Override
    public int hashCode() {
        return (int) (11 * id
                + brand.hashCode()
                + model.hashCode()
                + issueCountry.hashCode()
                + guaranteePeriod.hashCode()
                + price.hashCode()
                + created.hashCode()
                + changed.hashCode()
                + userOrderId.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Car car = (Car) object;

        return id == car.id
                && (brand == car.brand || brand != null && brand.equals(car.getBrand()))
                && (model == car.model || model != null && model.equals(car.getModel()))
                && (issueCountry == car.issueCountry || issueCountry != null && issueCountry.equals(car.getIssueCountry()))
                && (guaranteePeriod == car.guaranteePeriod || guaranteePeriod != null && guaranteePeriod.equals(car.getGuaranteePeriod()))
                && (price == car.price || price != null && price.equals(car.getPrice()))
                && (created == car.created || created != null && created.equals(car.getCreated()))
                && (changed == car.changed || changed != null && changed.equals(car.getChanged()))
                && (userOrderId == car.userOrderId || userOrderId != null && userOrderId.equals(car.getUserOrderId()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Car: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("brand ").append(brand).append(", ");
        stringBuilder.append("model ").append(model).append(", ");
        stringBuilder.append("issue country ").append(issueCountry).append(", ");
        stringBuilder.append("guarantee period ").append(guaranteePeriod).append(", ");
        stringBuilder.append("price ").append(price).append(", ");
        stringBuilder.append("created ").append(created).append(", ");
        stringBuilder.append("changed ").append(changed).append(", ");
        stringBuilder.append("user order id ").append(userOrderId);
        return stringBuilder.toString();
    }
}

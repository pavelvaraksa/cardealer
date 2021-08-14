package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;

public class Car {
    private Long id;

    private Model model;

    private Country issueCountry;

    private Integer guaranteePeriod;

    private Integer price;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long userOrderId;

    public Car() {
    }

    public Car(Model model,
               Country issueCountry,
               Integer guaranteePeriod,
               Integer price) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
        return "Car: " +
                "id = " + id +
                ", model is " + model +
                ", issue country is " + issueCountry +
                ", guarantee period = " + guaranteePeriod +
                ", price = " + price +
                ", created on " + created +
                ", changed on " + changed +
                ", user order id = " + userOrderId;
    }
}

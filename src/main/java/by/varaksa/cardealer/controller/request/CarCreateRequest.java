package by.varaksa.cardealer.controller.request;

import by.varaksa.cardealer.entity.Brand;

import java.sql.Blob;
import java.util.Date;

public class CarCreateRequest {
    private Long id;

    private Brand brand;

    private String model;

    private String issueCountry;

    private Integer guaranteePeriod;

    private Double price;

    private Blob image;

    private Date created;

    private Date changed;

    private Long userOrderId;

    public CarCreateRequest() {
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIssueCountry() {
        return issueCountry;
    }

    public void setIssueCountry(String issueCountry) {
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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
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
                + image.hashCode()
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

        CarCreateRequest car = (CarCreateRequest) object;

        return id == car.id
                && (brand == car.brand || brand != null && brand.equals(car.getBrand()))
                && (model == car.model || model != null && model.equals(car.getModel()))
                && (issueCountry == car.issueCountry || issueCountry != null && issueCountry.equals(car.getIssueCountry()))
                && (guaranteePeriod == car.guaranteePeriod || guaranteePeriod != null && guaranteePeriod.equals(car.getGuaranteePeriod()))
                && (price == car.price || price != null && price.equals(car.getPrice()))
                && (image == car.image || image != null && image.equals(car.getImage()))
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
        stringBuilder.append("image ").append(image).append(", ");
        stringBuilder.append("created ").append(created).append(", ");
        stringBuilder.append("changed ").append(changed).append(", ");
        stringBuilder.append("user order id ").append(userOrderId);
        return stringBuilder.toString();
    }
}

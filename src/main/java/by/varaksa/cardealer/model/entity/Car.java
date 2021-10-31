package by.varaksa.cardealer.model.entity;

import java.time.LocalDateTime;

/**
 * Class {@code Car} designed to store data about a car
 *
 * @author Pavel Varaksa
 */
public class Car {
    private Long id;

    private Model model;

    private Country issueCountry;

    private Integer guaranteePeriod;

    private Integer price;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Long dealerId;

    private String name;

    private FuelType fuelType;

    private Double volume;

    private TransmissionType transmissionType;

    private Integer gearsCount;

    private Color color;

    private BodyType bodyType;

    public Car() {
    }

    public Car(Model model, Country issueCountry, Integer guaranteePeriod, Integer price, Long dealerId) {
        this.model = model;
        this.issueCountry = issueCountry;
        this.guaranteePeriod = guaranteePeriod;
        this.price = price;
        this.dealerId = dealerId;
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

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Integer getGearsCount() {
        return gearsCount;
    }

    public void setGearsCount(Integer gearsCount) {
        this.gearsCount = gearsCount;
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

    @Override
    public int hashCode() {
        int result = id == null ? 0 : id.hashCode();
        result = 11 * result + (model == null ? 0 : model.hashCode());
        result = 11 * result + (issueCountry == null ? 0 : issueCountry.hashCode());
        result = 11 * result + (guaranteePeriod == null ? 0 : guaranteePeriod.hashCode());
        result = 11 * result + (price == null ? 0 : price.hashCode());
        result = 11 * result + (created == null ? 0 : created.hashCode());
        result = 11 * result + (changed == null ? 0 : changed.hashCode());
        result = 11 * result + (dealerId == null ? 0 : dealerId.hashCode());
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

        Car car = (Car) object;

        if (id != null ? !id.equals(car.id) : car.id != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (issueCountry != null ? !issueCountry.equals(car.issueCountry) : car.issueCountry != null) return false;
        if (guaranteePeriod != null ? !guaranteePeriod.equals(car.guaranteePeriod) : car.guaranteePeriod != null) return false;
        if (price != null ? !price.equals(car.price) : car.price != null) return false;
        if (created != null ? !created.equals(car.created) : car.created != null) return false;
        if (changed != null ? !changed.equals(car.changed) : car.changed != null) return false;
        if (dealerId != null ? !dealerId.equals(car.dealerId) : car.dealerId != null) return false;

        return true;
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
                ", dealer id = " + dealerId;
    }
}

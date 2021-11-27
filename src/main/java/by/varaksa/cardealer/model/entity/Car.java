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
}

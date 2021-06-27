package by.varaksa.cardealer.controller.request;

public class DealerCreateRequest {
    private Long id;

    private String name;

    private String address;

    private Integer foundationYear;

    private String city;

    private Long carId;

    public DealerCreateRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(Integer foundationYear) {
        this.foundationYear = foundationYear;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Override
    public int hashCode() {
        return (int) (11 * id
                + name.hashCode()
                + address.hashCode()
                + foundationYear.hashCode()
                + city.hashCode()
                + carId.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        DealerCreateRequest dealer = (DealerCreateRequest) object;

        return id == dealer.id
                && (name == dealer.name || name != null && name.equals(dealer.getName()))
                && (address == dealer.address || address != null && address.equals(dealer.getAddress()))
                && (foundationYear == dealer.foundationYear || foundationYear != null && foundationYear.equals(dealer.getFoundationYear()))
                && (city == dealer.city || city != null && city.equals(dealer.getCity()))
                && (carId == dealer.carId || carId != null && carId.equals(dealer.getCarId()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dealer: ");
        stringBuilder.append("id ").append(id).append(", ");
        stringBuilder.append("name ").append(name).append(", ");
        stringBuilder.append("address ").append(address).append(", ");
        stringBuilder.append("foundation year ").append(foundationYear).append(", ");
        stringBuilder.append("city ").append(city).append(", ");
        stringBuilder.append("car id ").append(carId);
        return stringBuilder.toString();
    }
}

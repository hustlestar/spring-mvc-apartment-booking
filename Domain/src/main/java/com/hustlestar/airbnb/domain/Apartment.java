package com.hustlestar.airbnb.domain;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
public class Apartment extends AbstractEntity {
    private Country country;
    private City city;
    private String address;
    private String title;
    private int guests;

    public Apartment() {
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (guests != apartment.guests) return false;
        if (country != null ? !country.equals(apartment.country) : apartment.country != null) return false;
        if (city != null ? !city.equals(apartment.city) : apartment.city != null) return false;
        if (address != null ? !address.equals(apartment.address) : apartment.address != null) return false;
        return title != null ? title.equals(apartment.title) : apartment.title == null;

    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + guests;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Apartment{");
        sb.append("country=").append(country);
        sb.append(", city=").append(city);
        sb.append(", address='").append(address).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", guests=").append(guests);
        sb.append('}');
        return sb.toString();
    }
}

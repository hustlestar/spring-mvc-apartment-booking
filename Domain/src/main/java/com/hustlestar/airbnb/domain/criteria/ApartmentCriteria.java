package com.hustlestar.airbnb.domain.criteria;

import java.io.Serializable;

/**
 * Created by Yauheni_Malashchytsk on 4/10/2017.
 */
public class ApartmentCriteria implements Serializable {
    private int country;
    private int city;
    private String title = "";
    private int guests;


    public ApartmentCriteria() {
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getCountry() {

        return country;
    }

    public void setCountry(int country) {
        this.country = country;
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApartmentCriteria{");
        sb.append("country=").append(country);
        sb.append(", city=").append(city);
        sb.append(", title='").append(title).append('\'');
        sb.append(", guests=").append(guests);
        sb.append('}');
        return sb.toString();
    }
}

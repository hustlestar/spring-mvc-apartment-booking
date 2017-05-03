package com.hustlestar.airbnb.domain;

/**
 * Created by Yauheni_Malashchytsk on 4/5/2017.
 */
public class City extends AbstractEntity {
    private String title;

    public City() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return title != null ? title.equals(city.title) : city.title == null;

    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return title;
    }
}

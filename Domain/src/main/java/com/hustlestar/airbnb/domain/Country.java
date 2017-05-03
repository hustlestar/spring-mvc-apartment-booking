package com.hustlestar.airbnb.domain;

/**
 * Created by Yauheni_Malashchytsk on 4/5/2017.
 */
public class Country extends AbstractEntity {
    private String title;

    public Country() {
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

        Country country = (Country) o;

        return title != null ? title.equals(country.title) : country.title == null;

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

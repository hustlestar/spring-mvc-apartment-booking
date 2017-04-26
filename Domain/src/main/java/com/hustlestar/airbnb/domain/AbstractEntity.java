package com.hustlestar.airbnb.domain;

import java.io.Serializable;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
public abstract class AbstractEntity implements Serializable{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

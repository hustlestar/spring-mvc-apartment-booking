package com.hustlestar.airbnb.service;

import com.hustlestar.airbnb.domain.Apartment;
import com.hustlestar.airbnb.domain.criteria.ApartmentCriteria;

import java.util.List;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
public interface ApartmentService {

    List<Apartment> getAvailableApartments();

    List<Apartment> getApartmentByCriteria(ApartmentCriteria criteria);

    List<Apartment> findApartment(String title);

}

package com.hustlestar.airbnb.service;

import com.hustlestar.airbnb.domain.Apartment;
import com.hustlestar.airbnb.domain.City;
import com.hustlestar.airbnb.domain.Country;
import com.hustlestar.airbnb.domain.criteria.ApartmentCriteria;
import com.hustlestar.airbnb.service.exc.ServiceException;

import java.util.List;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
public interface ApartmentService {

    List<Apartment> getAvailableApartments();

    List<Apartment> getApartmentByCriteria(ApartmentCriteria criteria);

    List<Apartment> findApartment(String title);

    Apartment getApartment(int id) throws ServiceException;

    List<Country> getCountries() throws ServiceException;

    List<City> getCities() throws ServiceException;
}

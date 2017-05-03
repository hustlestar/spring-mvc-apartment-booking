package com.hustlestar.airbnb.dao;

import com.hustlestar.airbnb.dao.exc.DAOException;
import com.hustlestar.airbnb.domain.Apartment;
import com.hustlestar.airbnb.domain.criteria.ApartmentCriteria;

import java.util.List;

/**
 * Created by Yauheni_Malashchytsk on 4/4/2017.
 */
public interface ApartmentDAO {

    List<Apartment> getAvailableApartments();

    List<Apartment> getApartmentByCriteria(ApartmentCriteria criteria);

    List<Apartment> findApartment(String title);

    Apartment getApartment(int id) throws DAOException;
}

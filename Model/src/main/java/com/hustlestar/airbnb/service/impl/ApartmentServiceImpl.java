package com.hustlestar.airbnb.service.impl;

import com.hustlestar.airbnb.dao.ApartmentDAO;
import com.hustlestar.airbnb.dao.exc.DAOException;
import com.hustlestar.airbnb.domain.Apartment;
import com.hustlestar.airbnb.domain.City;
import com.hustlestar.airbnb.domain.Country;
import com.hustlestar.airbnb.domain.criteria.ApartmentCriteria;
import com.hustlestar.airbnb.service.ApartmentService;
import com.hustlestar.airbnb.service.exc.ServiceException;
import com.hustlestar.airbnb.service.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentDAO apartmentDAO;


    public List<Apartment> getAvailableApartments() {

        return apartmentDAO.getAvailableApartments();
    }

    public List<Apartment> getApartmentByCriteria(ApartmentCriteria criteria) {

        return apartmentDAO.getApartmentByCriteria(criteria);
    }

    public List<Apartment> findApartment(String title) {
        if (Validation.validateString(title)) {
            return apartmentDAO.findApartment(title);
        } else {
            return Collections.emptyList();
        }
    }

    public Apartment getApartment(int id) throws ServiceException {
        try {
            return apartmentDAO.getApartment(id);
        } catch (DAOException e) {
            throw new ServiceException("No apartment with such id", e);
        }
    }

    public List<Country> getCountries() throws ServiceException {
        try {
            return apartmentDAO.getCountries();
        } catch (DAOException e) {
            throw new ServiceException("No countries", e);
        }
    }

    public List<City> getCities() throws ServiceException {
        try {
            return apartmentDAO.getCities();
        } catch (DAOException e) {
            throw new ServiceException("No cities", e);
        }
    }
}

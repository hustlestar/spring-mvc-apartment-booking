package com.hustlestar.airbnb.service.impl;

import com.hustlestar.airbnb.dao.ApartmentDAO;
import com.hustlestar.airbnb.domain.Apartment;
import com.hustlestar.airbnb.domain.criteria.ApartmentCriteria;
import com.hustlestar.airbnb.service.ApartmentService;
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
}

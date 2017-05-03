package com.hustlestar.airbnb.controller;

import com.hustlestar.airbnb.domain.Apartment;
import com.hustlestar.airbnb.domain.criteria.ApartmentCriteria;
import com.hustlestar.airbnb.service.ApartmentService;
import com.hustlestar.airbnb.service.exc.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Yauheni_Malashchytsk on 5/2/2017.
 */
@Controller
@RequestMapping(value = "/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView allApartments() {
        List<Apartment> apartmentList = apartmentService.getAvailableApartments();
        return new ModelAndView("apartments", "apartments", apartmentList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView findApartment(@RequestParam String title) {
        List<Apartment> apartmentList = apartmentService.findApartment(title);
        return new ModelAndView("apartments", "apartments", apartmentList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getApartment(@PathVariable int id) throws ServiceException {
        Apartment apartment = apartmentService.getApartment(id);
        return new ModelAndView("apartment", "apartment", apartment);
    }

    @RequestMapping(value = "/criteria/{country}{city}{guests}{title}")
    public ModelAndView getApartmentByCriteria(
            @MatrixVariable(name = "country", required = false, defaultValue = "0") int country,
            @MatrixVariable(name = "city", required = false, defaultValue = "0") int city,
            @MatrixVariable(name = "guests", required = false, defaultValue = "0") int guests,
            @MatrixVariable(name = "title", required = false, defaultValue = "") String title
    ) {
        ApartmentCriteria apartmentCriteria = new ApartmentCriteria();
        apartmentCriteria.setCity(city);
        apartmentCriteria.setCountry(country);
        apartmentCriteria.setTitle(title);
        apartmentCriteria.setGuests(guests);
        List<Apartment> apartmentList = apartmentService.getApartmentByCriteria(apartmentCriteria);
        return new ModelAndView("apartments", "apartments", apartmentList);
    }


}

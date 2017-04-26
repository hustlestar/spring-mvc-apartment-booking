package service;

import com.hustlestar.airbnb.dao.ApartmentDAO;
import com.hustlestar.airbnb.domain.Apartment;
import com.hustlestar.airbnb.domain.criteria.ApartmentCriteria;
import com.hustlestar.airbnb.service.ApartmentService;
import com.hustlestar.airbnb.service.impl.ApartmentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

/**
 * Created by Yauheni_Malashchytsk on 4/10/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApartmentServiceTest {

    @Mock
    private ApartmentDAO apartmentDAO;

    @InjectMocks
    private ApartmentService apartmentService = new ApartmentServiceImpl();

    @Test
    public void getApartmentByCriteriaTest() {
        List<Apartment> emptyApartments = new ArrayList<Apartment>();
        ApartmentCriteria criteria1 = new ApartmentCriteria();
        criteria1.setCity(-5);

        when(apartmentService.getApartmentByCriteria(criteria1)).
                thenReturn(emptyApartments);

        List<Apartment> result = apartmentService.getApartmentByCriteria(criteria1);

        assertEquals(emptyApartments.size(), result.size());
        assertSame(emptyApartments, result);
        verify(apartmentDAO).getApartmentByCriteria(criteria1);
    }

    @Test
    public void getApartmentsByCriteria2() {
        List<Apartment> apartments = new ArrayList<Apartment>();
        apartments.add(new Apartment());
        ApartmentCriteria criteria2 = new ApartmentCriteria();
        criteria2.setCountry(5);
        when(apartmentService.getApartmentByCriteria(criteria2)).
                thenReturn(apartments);

        List<Apartment> result = apartmentService.getApartmentByCriteria(criteria2);

        assertEquals(apartments.size(), result.size());
        assertSame(apartments, result);
        verify(apartmentDAO).getApartmentByCriteria(criteria2);
    }

    @Test
    public void getAvailableApartmentsTest() {
        List<Apartment> apartments = new ArrayList<Apartment>();
        apartments.add(new Apartment());
        apartments.add(new Apartment());
        apartments.add(new Apartment());
        when(apartmentDAO.getAvailableApartments()).
                thenReturn(apartments);

        List<Apartment> result = apartmentService.getAvailableApartments();
        assertEquals(apartments.size(), result.size());
        assertSame(apartments, result);
        verify(apartmentDAO).getAvailableApartments();
    }

    @Test
    public void findApartmentTest() {
        List<Apartment> emptyApartments = Collections.<Apartment>emptyList();

        String title1 = "";
        when(apartmentDAO.findApartment(title1)).
                thenReturn(Collections.<Apartment>emptyList());
        List<Apartment> result = apartmentService.findApartment(title1);

        assertEquals(emptyApartments.size(), result.size());
        assertSame(emptyApartments, result);
        verify(apartmentDAO, never()).findApartment(title1);


    }

    @Test
    public void findApartmentTest2() {
        List<Apartment> apartments = new ArrayList<Apartment>();
        apartments.add(new Apartment());
        apartments.add(new Apartment());
        apartments.add(new Apartment());
        String title2 = "flat";
        when(apartmentDAO.findApartment(title2)).
                thenReturn(apartments);

        List<Apartment> result = apartmentService.findApartment(title2);
        assertEquals(apartments.size(), result.size());
        assertSame(apartments, result);
        verify(apartmentDAO).findApartment(title2);
    }
}

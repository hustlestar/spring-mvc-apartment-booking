package com.hustlestar.airbnb.dao.orcl;

import com.hustlestar.airbnb.dao.ApartmentDAO;
import com.hustlestar.airbnb.dao.exc.DAOException;
import com.hustlestar.airbnb.domain.Apartment;
import com.hustlestar.airbnb.domain.City;
import com.hustlestar.airbnb.domain.Country;
import com.hustlestar.airbnb.domain.criteria.ApartmentCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yauheni_Malashchytsk on 4/4/2017.
 */
@Repository
public class ApartmentOracle extends AbstractDAO implements ApartmentDAO {

    private static final String ID = "A_ID";
    private static final String COUNTRY_ID = "A_CO_ID";
    private static final String COUNTRY_TITLE = "CO_TITLE";
    private static final String CITY_ID = "A_CI_ID";
    private static final String CITY_TITLE = "CI_TITLE";
    private static final String ADDRESS = "A_ADDRESS";
    private static final String TITLE = "A_TITLE";
    private static final String GUESTS = "A_GUESTS";

    public static final String GET_ALL_APARTMENTS =
            "SELECT A_ID, a_title, a_guests, a_address, a_co_id, countries.co_title, apartments.a_ci_id, cities.ci_title " +
                    "FROM APARTMENTS\n" +
                    "LEFT JOIN COUNTRIES \n" +
                    "ON a_co_id = countries.co_id\n" +
                    "LEFT JOIN cities\n" +
                    "ON apartments.a_ci_id = cities.ci_id\n" +
                    "AND countries.co_id=cities.ci_co_id\n";

    public static final String FIND_APARTMENT_BY_TITLE =
            "SELECT A_ID, a_title, a_guests, a_address, a_co_id, countries.co_title, apartments.a_ci_id, cities.ci_title " +
                    "FROM APARTMENTS\n" +
                    "LEFT JOIN COUNTRIES \n" +
                    "ON a_co_id = countries.co_id\n" +
                    "LEFT JOIN cities\n" +
                    "ON apartments.a_ci_id = cities.ci_id\n" +
                    "AND countries.co_id=cities.ci_co_id\n" +
                    "WHERE A_TITLE LIKE ?";
    public static final String GET_APARTMENT_BY_CRITERIA =
            "SELECT A_ID, a_title, a_guests, a_address, a_co_id, countries.co_title, apartments.a_ci_id, cities.ci_title " +
                    "FROM APARTMENTS\n" +
                    "LEFT JOIN COUNTRIES \n" +
                    "ON a_co_id = countries.co_id\n" +
                    "LEFT JOIN cities\n" +
                    "ON apartments.a_ci_id = cities.ci_id\n" +
                    "AND countries.co_id=cities.ci_co_id " +
                    "WHERE (A_CO_ID=? OR ?=0) " +
                    "AND (A_CI_ID=? OR ?=0) " +
                    "AND (A_GUESTS>? OR ?=0) " +
                    "AND (A_TITLE LIKE ? OR ? IS NULL) ";
    public static final String GET_APARTMENT_BY_ID =
            "SELECT A_ID, a_title, a_guests, a_address, a_co_id, countries.co_title, apartments.a_ci_id, cities.ci_title " +
                    "FROM APARTMENTS\n" +
                    "LEFT JOIN COUNTRIES \n" +
                    "ON a_co_id = countries.co_id\n" +
                    "LEFT JOIN cities\n" +
                    "ON apartments.a_ci_id = cities.ci_id\n" +
                    "AND countries.co_id=cities.ci_co_id\n" +
                    "WHERE A_ID=?";

    public List<Apartment> getAvailableApartments() {
        return getJdbcTemplate().query(
                GET_ALL_APARTMENTS,
                new Object[]{},
                getRowMapper());
    }

    public List<Apartment> getApartmentByCriteria(ApartmentCriteria criteria) {
        StringBuilder sb = new StringBuilder("%").append(criteria.getTitle()).append("%");
        return getJdbcTemplate().query(
                GET_APARTMENT_BY_CRITERIA,
                new Object[]{
                        criteria.getCountry(), criteria.getCountry(),
                        criteria.getCity(), criteria.getCity(),
                        criteria.getGuests(), criteria.getGuests(),
                        sb.toString(), sb.toString()
                },
                getRowMapper()
        );
    }

    public List<Apartment> findApartment(String title) {
        StringBuilder sb = new StringBuilder("%").append(title).append("%");
        return getJdbcTemplate().query(
                FIND_APARTMENT_BY_TITLE,
                new Object[]{sb.toString()},
                getRowMapper());
    }

    public Apartment getApartment(int id) throws DAOException {
        try {
            return getJdbcTemplate().queryForObject(
                    GET_APARTMENT_BY_ID,
                    new Object[]{id},
                    getRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            throw new DAOException("Empty result set", e);
        }
    }

    private RowMapper<Apartment> getRowMapper() {
        return new RowMapper<Apartment>() {
            public Apartment mapRow(ResultSet resultSet, int i) throws SQLException {
                Apartment apartment = new Apartment();
                apartment.setId(resultSet.getInt(ID));
                Country country = new Country();
                country.setId(resultSet.getInt(COUNTRY_ID));
                country.setTitle(resultSet.getString(COUNTRY_TITLE));
                apartment.setCountry(country);
                City city = new City();
                city.setId(resultSet.getInt(CITY_ID));
                city.setTitle(resultSet.getString(CITY_TITLE));
                apartment.setCity(city);
                apartment.setAddress(resultSet.getString(ADDRESS));
                apartment.setTitle(resultSet.getString(TITLE));
                apartment.setGuests(resultSet.getInt(GUESTS));
                return apartment;
            }
        };
    }
}

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
    public static final String CI_TITLE = "CI_TITLE";
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
    public static final String GET_COUNTRIES = "SELECT * FROM countries";
    public static final String GET_CITIES = "SELECT * FROM cities";

    public static final String CO_ID = "CO_ID";
    public static final String CO_TITLE = "CO_TITLE";
    public static final String CI_ID = "CI_ID";
    public static final String CI_CO_ID = "CI_CO_ID";

    public List<Apartment> getAvailableApartments() throws DAOException {
        try {
            return getJdbcTemplate().query(
                    GET_ALL_APARTMENTS,
                    new Object[]{},
                    getApartmentRowMapper());
        } catch (DataAccessException e) {
            throw new DAOException("No apartments available", e);
        }
    }

    public List<Apartment> getApartmentByCriteria(ApartmentCriteria criteria) throws DAOException {
        StringBuilder sb = new StringBuilder("%").append(criteria.getTitle()).append("%");
        try {
            return getJdbcTemplate().query(
                    GET_APARTMENT_BY_CRITERIA,
                    new Object[]{
                            criteria.getCountry(), criteria.getCountry(),
                            criteria.getCity(), criteria.getCity(),
                            criteria.getGuests(), criteria.getGuests(),
                            sb.toString(), sb.toString()
                    },
                    getApartmentRowMapper()
            );
        } catch (DataAccessException e) {
            throw new DAOException("No apartments available with such criteria", e);
        }
    }

    public List<Apartment> findApartment(String title) throws DAOException {
        StringBuilder sb = new StringBuilder("%").append(title).append("%");
        try {
            return getJdbcTemplate().query(
                    FIND_APARTMENT_BY_TITLE,
                    new Object[]{sb.toString()},
                    getApartmentRowMapper());
        } catch (DataAccessException e) {
            throw new DAOException("No apartments available found", e);
        }
    }

    public Apartment getApartment(int id) throws DAOException {
        try {
            return getJdbcTemplate().queryForObject(
                    GET_APARTMENT_BY_ID,
                    new Object[]{id},
                    getApartmentRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            throw new DAOException("Empty result set", e);
        }
    }

    public List<Country> getCountries() throws DAOException {
        try {
            return getJdbcTemplate().query(
                    GET_COUNTRIES,
                    new Object[]{},
                    new RowMapper<Country>() {
                        public Country mapRow(ResultSet resultSet, int i) throws SQLException {
                            Country country = new Country();
                            country.setId(resultSet.getInt(CO_ID));
                            country.setTitle(resultSet.getString(CO_TITLE));
                            return country;
                        }
                    }
            );
        } catch (DataAccessException e) {
            throw new DAOException("No countries available", e);
        }
    }

    public List<City> getCities() throws DAOException {
        try {
            return getJdbcTemplate().query(
                    GET_CITIES,
                    new Object[]{},
                    new RowMapper<City>() {
                        public City mapRow(ResultSet resultSet, int i) throws SQLException {
                            City city = new City();
                            city.setId(resultSet.getInt(CI_ID));
                            city.setTitle(resultSet.getString(CI_TITLE));
                            city.setCountry(resultSet.getInt(CI_CO_ID));
                            return city;

                        }
                    }
            );
        } catch (DataAccessException e) {
            throw new DAOException("No cities available", e);
        }
    }

    private RowMapper<Apartment> getApartmentRowMapper() {
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

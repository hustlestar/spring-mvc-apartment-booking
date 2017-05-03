package com.hustlestar.airbnb.dao.orcl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


/**
 * Created by Yauheni_Malashchytsk on 4/4/2017.
 */
@Repository
public abstract class AbstractDAO extends JdbcDaoSupport{

    @Autowired
    private void setSource(DataSource source) {
        this.setDataSource(source);
    }

}

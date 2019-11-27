package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Route;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.RouteDAO;
import org.springframework.stereotype.Repository;

@Repository
public class RouteDAOImpl extends AbstractDAO<Route>
        implements RouteDAO {

    public RouteDAOImpl() {
        super(Route.class);
    }

}
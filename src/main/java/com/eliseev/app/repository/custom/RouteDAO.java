package com.eliseev.app.repository.custom;


import com.eliseev.app.models.Point;
import com.eliseev.app.models.Route;
import com.eliseev.app.repository.IDAO;
import com.eliseev.app.services.dto.RouteDTO;

import java.util.List;

public interface RouteDAO extends IDAO<Route> {

    List<Route> getRoutes(Point depPointObj, Point arrPointObj);

    RouteDTO getRouteDetails(long routeId, Point depPointObj, Point arrPointObj);
}

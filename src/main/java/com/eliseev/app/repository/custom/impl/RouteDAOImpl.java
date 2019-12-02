package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Company;
import com.eliseev.app.models.Point;
import com.eliseev.app.models.Route;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.RouteDAO;
import com.eliseev.app.services.dto.RouteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RouteDAOImpl extends AbstractDAO<Route>
        implements RouteDAO {

    private Logger logger = LoggerFactory.getLogger(RouteDAOImpl.class);

    public RouteDAOImpl() {
        super(Route.class);
    }

    @Override
    public List<Route> getRoutes(Point depPointObj, Point arrPointObj) {

        @SuppressWarnings("unchecked")
        List<Object[]> objects = super.entityManager.createNativeQuery("" +
                "select routeId, company_id, name as companyName \n" +
                "from (\n" +
                "   select routeId, company_id from map \n" +
                "       left join  (\n" +
                "                 select r.id as routeId, company_id, min(serial_number), m.start_point_id, m.end_point_id\n" +
                "                 from map m \n" +
                "                   inner join route r on r.id = m.route_id\n" +
                "                 where m.start_point_id =  :depPointId\n" +
                "                   or m.end_point_id = :arrPointId\n" +
                "                 group by routeId\n" +
                "                 having count(routeId) >= 2 or (m.start_point_id = :depPointId and m.end_point_id = :arrPointId)\n" +
                "                ) as tab on map.route_id = tab.routeId\n" +
                "     where map.start_point_id = :depPointId and routeId is not null and company_id is not null\n" +
                "   ) as tab1 \n" +
                "left join company c on c.id = company_id")
                .setParameter("depPointId", depPointObj.getId())
                .setParameter("arrPointId", arrPointObj.getId())
                .getResultList();

        List<Route> routes = new ArrayList<>();
        Route route;
        Company company;

        for (Object[] object : objects) {
            company = new Company(((BigInteger) object[1]).longValue(), (String) object[2]);
            route = new Route(((BigInteger) object[0]).longValue(), company);
            routes.add(route);
        }

        return routes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public RouteDTO getRouteDetails(long routeId, Point depPointObj, Point arrPointObj) {

        RouteDTO routeDTO = new RouteDTO();

        List<Object[]> objects1 = super.entityManager.createNativeQuery("select m.id, m.serial_number from Map m where m.start_point_id = :depStationId\n" +
                "and m.route_id = :routeId\n")
                .setParameter("depStationId", depPointObj.getId())
                .setParameter("routeId", routeId)
                .getResultList();

        Object[] object1 = objects1.get(0);
        routeDTO.setStartMapId(((BigInteger) object1[0]).longValue());
        routeDTO.setStartMapSerialNumber((int) object1[1]);

        List<Object[]> objects2 = super.entityManager.createNativeQuery("select m.id,m.serial_number from Map m where m.end_point_id = :endStationId\n" +
                "and m.route_id = :routeId\n")
                .setParameter("endStationId", arrPointObj.getId())
                .setParameter("routeId", routeId)
                .getResultList();

        Object[] object2 = objects2.get(0);
        routeDTO.setEndMapId(((BigInteger) object2[0]).longValue());
        routeDTO.setEndMapSerialNumber((int) object2[1]);

        List<Object[]> objects = super.entityManager.createNativeQuery("" +
                "select sum(distance), group_concat(distinct name), sum(cost_per_km * distance) from (\n" +
                "select * from map \n" +
                "where map.route_id = :routeId and serial_number between :leftBorder and :rightBorder\n" +
                ") as tab\n" +
                "inner join transport t on t.id = transport_id")
                .setParameter("leftBorder", routeDTO.getStartMapSerialNumber())
                .setParameter("rightBorder", routeDTO.getEndMapSerialNumber())
                .setParameter("routeId", routeId)
                .getResultList();

        Object[] obj = objects.get(0);
        logger.info("{}", obj);
        String[] transports = ((String) obj[1]).split(",");

        routeDTO.setCost((BigDecimal) obj[2]);
        routeDTO.setDistance(((BigDecimal) obj[0]).longValue());
        routeDTO.setTransports(transports);
        logger.info("{}",routeDTO);
        return routeDTO;
    }
}
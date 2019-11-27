package com.eliseev.app.services;


import com.eliseev.app.models.Map;
import com.eliseev.app.models.Point;
import com.eliseev.app.models.Route;
import com.eliseev.app.repository.custom.RouteDAO;
import com.eliseev.app.services.dto.MapDTO;
import com.eliseev.app.services.dto.RoutesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService extends AbstractService<Route, RouteDAO> {

    private TransportService transportService;
    private PointService pointService;
    private MapService mapService;

    @Autowired
    public RouteService(RouteDAO dao, TransportService transportService, PointService pointService,
                        MapService mapService) {
        super(dao);
        this.transportService = transportService;
        this.pointService = pointService;
        this.mapService = mapService;
    }

    private Logger logger = LoggerFactory.getLogger(RouteService.class);

    @Transactional
    public List<RoutesDTO> startEndRoutePointsList() {

        List<Route> routes = dao.findAll();
        List<Map> maps;
        List<RoutesDTO> routesDTOs = new ArrayList<>();
        RoutesDTO routesDTO;
        Point startPoint, endPoint;
        int mapsSize;

        for (Route route : routes) {

            maps = mapService.list(route.getId());
            mapsSize = maps.size();

            if (mapsSize > 0) {
                startPoint = maps.get(0).getStartPoint();
                if (mapsSize == 1) {
                    endPoint = maps.get(0).getEndPoint();
                } else {
                    endPoint = maps.get(mapsSize - 1).getEndPoint();
                }
                routesDTO = new RoutesDTO(startPoint, endPoint);
                routesDTOs.add(routesDTO);
            }
        }

        return routesDTOs;

    }


    @Transactional
    public Route create(List<MapDTO> mapDTOs) {

        Route route = new Route();
        List<Map> maps = new ArrayList<>();
        Map map;

        for (MapDTO mapDTO : mapDTOs) {
            map = new Map(mapDTO.getDistance(), mapDTO.getSerialNumber(),
                    pointService.get(mapDTO.getStartPointId()),
                    pointService.get(mapDTO.getEndPointId()),
                    transportService.get(mapDTO.getTransportId()), route);
            maps.add(map);
        }

        route.getMaps().addAll(maps);

        return dao.save(route);
    }

}


package com.eliseev.app.service;

import com.eliseev.app.TestConfig;
import com.eliseev.app.models.Route;
import com.eliseev.app.services.RouteService;
import com.eliseev.app.services.dto.MapDTO;
import com.eliseev.app.services.dto.RoutesDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RouteServiceTest {

    Logger logger = LoggerFactory.getLogger(RouteServiceTest.class);

    @Autowired
    private RouteService routeService;



    @Test
    public void getRoutes() {
        List<RoutesDTO> routesDTOs = routeService.startEndRoutePointsList();
        logger.info("{}", routesDTOs);
    }

    @Test
    public void createRoute() {
        MapDTO mapDTO1 = new MapDTO(4L, 3L, null, 1L, 200L, 1);
        MapDTO mapDTO2 = new MapDTO(3L, 2L, null, 1L, 200L, 2);
        MapDTO mapDTO3 = new MapDTO(2L, 1L, null, 1L, 200L, 3);
        List<MapDTO> mapDTOs = new ArrayList<>();
        mapDTOs.add(mapDTO1);
        mapDTOs.add(mapDTO2);
        mapDTOs.add(mapDTO3);
        Route route = routeService.create(mapDTOs);

        logger.info("{}", route.getMaps());
    }

}

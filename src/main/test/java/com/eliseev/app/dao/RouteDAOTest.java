package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.repository.custom.RouteDAO;
import com.eliseev.app.services.PointService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RouteDAOTest {

    @Autowired
    private RouteDAO routeDAO;

    @Autowired
    private PointService pointService;

    @Test
    public void getRoutes() {
        routeDAO.getRoutes(pointService.get(1L), pointService.get(4L));
    }

    @Test
    public void getRoteDetails() {
        routeDAO.getRouteDetails(1, pointService.get(1L), pointService.get(4L));
    }

}

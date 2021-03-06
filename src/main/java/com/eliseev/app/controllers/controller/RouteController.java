package com.eliseev.app.controllers.controller;

import com.eliseev.app.services.PointService;
import com.eliseev.app.services.RouteService;
import com.eliseev.app.services.TransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private Logger logger = LoggerFactory.getLogger(RouteController.class);
    private RouteService routeService;
    private PointService pointService;
    private TransportService transportService;

    @Autowired
    public RouteController(RouteService routeService,
                           PointService pointService,
                           TransportService transportService) {
        this.routeService = routeService;
        this.pointService = pointService;
        this.transportService = transportService;
    }

    @GetMapping("/suitable")
    public String getRoutes(@RequestParam("dep_point") String depPoint,
                            @RequestParam("arr_point") String arrPoint,
                            Model model) {
        model.addAttribute("routes",routeService.getRoutesWithDetails(depPoint, arrPoint));
        model.addAttribute("dep_point", depPoint);
        model.addAttribute("arr_point", arrPoint);
        return "routes/filteredRoutes";
    }

    @GetMapping
    public String findAll(Model model) {
        logger.info("User send /routes");
        model.addAttribute("routes", routeService.startEndRoutePointsList());
        return "routes/routes";
    }

    @GetMapping("/form")
    public String getRouteForm(Model model) {
        logger.info("User send /routes/form");
        model.addAttribute("points", pointService.list());
        model.addAttribute("transports", transportService.list());
        return "routes/mapsForm";
    }



}


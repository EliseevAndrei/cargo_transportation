package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.Route;
import com.eliseev.app.services.MapService;
import com.eliseev.app.services.RouteService;
import com.eliseev.app.services.dto.DetailedMapDTO;
import com.eliseev.app.services.dto.MapDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/routes/list", produces = "application/json")
public class RouteRestController extends AbstractRestController<Route, RouteService> {

    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    private MapService mapService;

    @Autowired
    public RouteRestController(RouteService routeService,
                               MapService mapService) {
        super(routeService);
        this.mapService = mapService;
    }

    @PostMapping("/maps-sequence")
    public Route create(@RequestBody List<MapDTO> mapDTOs, @PathVariable(required = false) long ...id) {
        logger.info("User send POST /routes/list/maps-sequence with body {}", mapDTOs);
        return service.create(mapDTOs);
    }

    @GetMapping("/{routeId}/mapsBetween")
    public List<DetailedMapDTO> getMaps(@PathVariable("routeId") long routeId,
                                        @RequestParam("leftBorder") long leftBorder,
                                        @RequestParam("rightBorder") long rightBorder) {

        return mapService.getMaps(routeId, leftBorder, rightBorder);
    }

}

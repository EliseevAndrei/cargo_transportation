package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.Point;
import com.eliseev.app.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/points/list", produces = "application/json")
public class PointRestController extends AbstractRestController<Point, PointService> {

    @Autowired
    public PointRestController(PointService service) {
        super(service);
    }
}

package com.eliseev.app.controllers.controller;

import com.eliseev.app.services.PointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/points")
public class PointController {

    private final Logger logger = LoggerFactory.getLogger(PointController.class);

    private PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping
    public String getPoints(Model model) {
        logger.info("user send GET /points");
        model.addAttribute("points", pointService.list());
        return "points/points";
    }

}

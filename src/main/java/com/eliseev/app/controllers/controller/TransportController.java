package com.eliseev.app.controllers.controller;

import com.eliseev.app.services.TransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transports")
public class TransportController {

    private final Logger logger = LoggerFactory.getLogger(PointController.class);

    private TransportService transportService;

    @Autowired
    public TransportController(TransportService pointService) {
        this.transportService = pointService;
    }

    @GetMapping
    public String getPoints(Model model) {
        logger.info("user send GET /transports");
        model.addAttribute("transports", transportService.list());
        return "transports/transports";
    }

}


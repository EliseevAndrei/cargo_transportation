package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.Transport;
import com.eliseev.app.services.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/transports/list", produces = "application/json")
public class TransportRestController extends AbstractRestController<Transport, TransportService> {

    @Autowired
    public TransportRestController(TransportService service) {
        super(service);
    }
}

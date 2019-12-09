package com.eliseev.app.services;

import com.eliseev.app.models.Transport;
import com.eliseev.app.repository.custom.TransportDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransportService extends AbstractService<Transport, TransportDAO> {

    @Autowired
    public TransportService(TransportDAO dao) {
        super(dao);
    }

    private Logger logger = LoggerFactory.getLogger(PointService.class);

    public Transport getTransportByName(String name) {
        return super.dao.getTransportByName(name);
    }


    public Map<Transport, Long> getTransports(Long routeId, int startMapSerialNumber, int endMapSerialNumber) {
        return super.dao.getTransports(routeId, startMapSerialNumber, endMapSerialNumber);
    }
}

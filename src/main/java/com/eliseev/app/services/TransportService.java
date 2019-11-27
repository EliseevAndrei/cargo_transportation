package com.eliseev.app.services;

import com.eliseev.app.models.Transport;
import com.eliseev.app.repository.custom.TransportDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportService extends AbstractService<Transport, TransportDAO> {

    @Autowired
    public TransportService(TransportDAO dao) {
        super(dao);
    }

    private Logger logger = LoggerFactory.getLogger(PointService.class);


}

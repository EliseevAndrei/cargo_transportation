package com.eliseev.app.services;

import com.eliseev.app.models.Point;
import com.eliseev.app.repository.custom.PointDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService extends AbstractService<Point, PointDAO> {

    @Autowired
    public PointService(PointDAO dao) {
        super(dao);
    }

    private Logger logger = LoggerFactory.getLogger(PointService.class);

    public Point findPointByName(String name) {
        return super.dao.findPointByName(name);
    }

}

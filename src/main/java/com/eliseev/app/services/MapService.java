package com.eliseev.app.services;

import com.eliseev.app.models.Map;
import com.eliseev.app.repository.custom.MapDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService extends AbstractService<Map, MapDAO> {


    @Autowired
    public MapService(MapDAO dao) {
        super(dao);
    }

    private Logger logger = LoggerFactory.getLogger(MapService.class);

    public List<Map> list(long routeId) {
        return super.dao.list(routeId);
    }

}

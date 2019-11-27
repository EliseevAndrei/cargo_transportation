package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Map;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.MapDAO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MapDAOImpl extends AbstractDAO<Map>
        implements MapDAO {

    public MapDAOImpl() {
        super(Map.class);
    }

    public List<Map> list(long routeId) {
        return super.entityManager.createQuery("select m from Map m where m.route.id = :routeId order by m.serialNumber", Map.class)
                .setParameter("routeId", routeId)
                .getResultList();
    }

}
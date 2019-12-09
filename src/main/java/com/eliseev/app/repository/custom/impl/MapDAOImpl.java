package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Map;
import com.eliseev.app.models.Transport;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.MapDAO;
import com.eliseev.app.services.dto.DetailedMapDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MapDAOImpl extends AbstractDAO<Map>
        implements MapDAO {

    private Logger logger = LoggerFactory.getLogger(MapDAOImpl.class);

    public MapDAOImpl() {
        super(Map.class);
    }

    @Override
    public List<Map> list(long routeId) {
        return super.entityManager.createQuery("select m from Map m where m.route.id = :routeId order by m.serialNumber", Map.class)
                .setParameter("routeId", routeId)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DetailedMapDTO> getMaps(long routeId, long leftSerialNumber, long rightSerialNumber) {
        List<Object[]> objects =  super.entityManager.createNativeQuery("select ta.id as mapId, p1.name as depPoint, p2.name as arrPoint, distance,transport.id, transport.name, speed, max_weight as maxWeight, cost_per_km as costPerKm from (\n" +
                "select * from map\n" +
                "where route_id = :routeId\n" +
                "and serial_number between :leftSerialNumber and :rightSerialNumber\n" +
                ") as ta\n" +
                "inner join transport on transport.id = transport_id\n" +
                "inner join point as p1 on p1.id = start_point_id\n" +
                "inner join point as p2 on p2.id = end_point_id")
                .setParameter("leftSerialNumber", leftSerialNumber)
                .setParameter("rightSerialNumber", rightSerialNumber)
                .setParameter("routeId", routeId)
                .getResultList();

        DetailedMapDTO detailedMapDTO;
        List<DetailedMapDTO> detailedMapDTOS = new ArrayList<>();
        for (Object[] object : objects) {

            Transport transport = new Transport(
                    ((BigInteger)object[4]).longValue(),
                    (String) object[5], (Integer) object[6],
                    (Integer) object[7],
                    ((BigDecimal) object[8]).doubleValue());

            DetailedMapDTO detailedMap = new DetailedMapDTO(
                    ((BigInteger) object[0]).longValue(),
                    (String) object[1],
                    (String) object[2],
                    ((BigInteger)object[3]).longValue(),
                    transport);

            detailedMapDTOS.add(detailedMap);
        }
        return detailedMapDTOS;
    }
}
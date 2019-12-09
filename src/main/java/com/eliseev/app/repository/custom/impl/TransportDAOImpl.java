package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Transport;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TransportDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransportDAOImpl extends AbstractDAO<Transport>
        implements TransportDAO {

    public TransportDAOImpl() {
        super(Transport.class);
    }

    @Override
    public Transport getTransportByName(String name) {
        Transport transport;
        try {
            transport = super.entityManager.createQuery("select p from Transport p where p.name = :name", Transport.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return transport;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Transport, Long> getTransports(Long routeId, int startMapSerialNumber, int endMapSerialNumber) {
        /*return super.entityManager.createQuery(
                "select t from Transport t\n" +
                        "where t.id in (\n" +
                        "    select m.transport.id from Map m\n" +
                        "    where m.route.id = :routeId and m.serialNumber between :startMapSerialNumber and :endMapSerialNumber\n" +
                        ") ", Transport.class)
                .setParameter("routeId", routeId)
                .setParameter("startMapSerialNumber", startMapSerialNumber)
                .setParameter("endMapSerialNumber", endMapSerialNumber)
                .getResultList();*/
        List<Tuple> objects = super.entityManager.createNativeQuery(
                "select t.id, t.cost_per_km, t.max_weight, t.name, t.speed, sum(distance) as sumDistance from transport t\n" +
                        "inner join map on t.id = map.transport_id\n" +
                        "where route_id = :routeId and serial_number between :startMapSerialNumber and :endMapSerialNumber\n" +
                        "group by t.id", Tuple.class)
                .setParameter("routeId", routeId)
                .setParameter("startMapSerialNumber", startMapSerialNumber)
                .setParameter("endMapSerialNumber", endMapSerialNumber)
                .getResultList();

        Transport transport;
        Map<Transport, Long> transportDistance = new HashMap<>();
        Long sumDistance;
        for (Tuple tuple : objects) {
            transport = new Transport();
            transport.setId(((BigInteger)tuple.get("id")).longValue());
            transport.setCostPerKm(((BigDecimal) tuple.get("cost_per_km")).doubleValue());
            transport.setMaxWeight((Integer) tuple.get("max_weight"));
            transport.setName((String) tuple.get("name"));
            transport.setSpeed((Integer) tuple.get("speed"));
            sumDistance = ((BigDecimal) tuple.get("sumDistance")).longValue();

            transportDistance.put(transport, sumDistance);
        }

        return transportDistance;

    }

}


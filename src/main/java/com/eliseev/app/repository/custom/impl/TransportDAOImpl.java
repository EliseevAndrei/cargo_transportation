package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Transport;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TransportDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

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

}


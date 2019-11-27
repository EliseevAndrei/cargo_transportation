package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Transport;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TransportDAO;
import org.springframework.stereotype.Repository;

@Repository
public class TransportDAOImpl extends AbstractDAO<Transport>
        implements TransportDAO {

    public TransportDAOImpl() {
        super(Transport.class);
    }

}


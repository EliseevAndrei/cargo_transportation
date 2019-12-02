package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.OrderUser;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.OrderDAO;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl extends AbstractDAO<OrderUser>
        implements OrderDAO {

    public OrderDAOImpl() {
        super(OrderUser.class);
    }

}

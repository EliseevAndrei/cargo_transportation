package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Point;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.PointDAO;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl extends AbstractDAO<Point>
        implements PointDAO {

    public PointDAOImpl() {
        super(Point.class);
    }

}
package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Point;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.PointDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class PointDAOImpl extends AbstractDAO<Point>
        implements PointDAO {

    public PointDAOImpl() {
        super(Point.class);
    }

    @Override
    public Point findPointByName(String name) {
        Point point;
        try {
            point = super.entityManager.createQuery("select p from Point p where p.name = :name", Point.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return point;
    }
}
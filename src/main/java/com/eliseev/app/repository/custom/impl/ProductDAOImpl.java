package com.eliseev.app.repository.custom.impl;


import com.eliseev.app.models.Product;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.ProductDAO;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl extends AbstractDAO<Product>
        implements ProductDAO {

    public ProductDAOImpl() {
        super(Product.class);
    }
}
package com.eliseev.app.services;


import com.eliseev.app.models.Product;
import com.eliseev.app.repository.custom.ProductDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractService<Product, ProductDAO> {

    @Autowired
    public ProductService(ProductDAO dao) {
        super(dao);
    }

    private Logger logger = LoggerFactory.getLogger(ProductService.class);


}

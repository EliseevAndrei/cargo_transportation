package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.Product;
import com.eliseev.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/products/list", produces = "application/json")
public class ProductRestController extends AbstractRestController<Product, ProductService> {

    @Autowired
    public ProductRestController(ProductService service) {
        super(service);
    }
}


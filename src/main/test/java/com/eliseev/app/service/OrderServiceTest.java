package com.eliseev.app.service;

import com.eliseev.app.TestConfig;
import com.eliseev.app.models.Product;
import com.eliseev.app.models.Route;
import com.eliseev.app.services.OrderService;
import com.eliseev.app.services.ProductService;
import com.eliseev.app.services.dto.OrderDTO;
import com.eliseev.app.services.dto.ProductDTO;
import com.eliseev.app.services.dto.RouteDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Test
    public void getCostAndCoefficient() {
        OrderDTO orderDTO = new OrderDTO();
        RouteDTO routeDTO = new RouteDTO();
        Route route = new Route();
        route.setId(1L);
        routeDTO.setRoute(route);
        routeDTO.setStartMapSerialNumber(1);
        routeDTO.setEndMapSerialNumber(3);
        orderDTO.setRouteDTO(routeDTO);

        Product product1 = productService.get(1L);
        Product product2 = productService.get(2L);
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setProduct(product1);
        productDTO1.setWeight(12.0);
        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setProduct(product2);
        productDTO2.setWeight(11.0);

        List<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS.add(productDTO1);
        productDTOS.add(productDTO2);
        /*orderDTO.setProductWeight(productDoubleMap);*/
        orderDTO.setProductDTOs(productDTOS);
        orderService.createOrder(orderDTO);
    }

}

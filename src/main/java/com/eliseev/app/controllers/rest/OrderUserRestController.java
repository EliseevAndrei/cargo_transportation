package com.eliseev.app.controllers.rest;


import com.eliseev.app.models.OrderUser;
import com.eliseev.app.services.OrderService;
import com.eliseev.app.services.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders/list", produces = "application/json")
public class OrderUserRestController extends AbstractRestController<OrderUser, OrderService>{

    private Logger logger = LoggerFactory.getLogger(OrderUserRestController.class);

    @Autowired
    public OrderUserRestController(OrderService service) {
        super(service);
    }

    @PostMapping("/filled")
    public OrderDTO create(@RequestBody OrderDTO orderDTO) {
        logger.info("{}", orderDTO);
        return service.createOrder(orderDTO);
    }

}

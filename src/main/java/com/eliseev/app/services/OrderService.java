package com.eliseev.app.services;

import com.eliseev.app.models.OrderUser;
import com.eliseev.app.models.Transport;
import com.eliseev.app.repository.custom.OrderDAO;
import com.eliseev.app.services.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService extends AbstractService<OrderUser, OrderDAO> {

    private TransportService transportService;

    @Autowired
    public OrderService(OrderDAO dao,
                        TransportService transportService) {
        super(dao);
        this.transportService = transportService;
    }

    private Logger logger = LoggerFactory.getLogger(PointService.class);


    public void getCost(String[] transports, List<ProductDTO> productDTOs) {

        List<Transport> transportsObj =  Arrays.stream(transports)
                .map(transportService::getTransportByName)
                .collect(Collectors.toList());




    }


}

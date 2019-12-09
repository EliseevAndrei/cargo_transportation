package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.models.Transport;
import com.eliseev.app.repository.custom.TransportDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TransportDAOTest {

    Logger logger = LoggerFactory.getLogger(TransportDAOTest.class);

    @Autowired
    private TransportDAO transportDAO;

    @Test
    public void getTransports() {
        Map<Transport, Long> transports = transportDAO.getTransports(1L, 1, 3);
        logger.info("{}", transports);
    }

}

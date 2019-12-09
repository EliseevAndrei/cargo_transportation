package com.eliseev.app.repository.custom;

import com.eliseev.app.models.Transport;
import com.eliseev.app.repository.IDAO;

import java.util.Map;

public interface TransportDAO extends IDAO<Transport> {

    Transport getTransportByName(String name);

    Map<Transport, Long> getTransports(Long routeId, int startMapSerialNumber, int endMapSerialNumber);
}

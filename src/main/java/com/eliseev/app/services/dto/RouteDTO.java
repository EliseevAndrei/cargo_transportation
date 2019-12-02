package com.eliseev.app.services.dto;


import com.eliseev.app.models.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RouteDTO {

    private Route route;
    private long distance;
    private BigDecimal cost;
    private String[] transports;
    private long startMapId;
    private long startMapSerialNumber;
    private long endMapId;
    private long endMapSerialNumber;

}

package com.eliseev.app.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MapDTO {

    private Long startPointId;
    private Long endPointId;
    private Long routeId;
    private Long transportId;
    private Long distance;
    private Integer serialNumber;

}

package com.eliseev.app.services.dto;

import com.eliseev.app.models.Transport;
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
public class DetailedMapDTO {

    private long mapId;
    private String depPoint;
    private String arrPoint;
    private long distance;
    private Transport transport;

}

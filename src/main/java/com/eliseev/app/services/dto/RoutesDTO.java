package com.eliseev.app.services.dto;

import com.eliseev.app.models.Point;
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
public class RoutesDTO {

    private Point startPoint;
    private Point endPoint;

}

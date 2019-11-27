package com.eliseev.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Map extends AbstractEntity {

    private Long distance;
    @Column(name="serial_number")
    private Integer serialNumber;

    @ManyToOne
    @JoinColumn(name="start_point_id")
    private Point startPoint;

    @ManyToOne
    @JoinColumn(name="end_point_id")
    private Point endPoint;

    @ManyToOne
    @JoinColumn(name="transport_id")
    private Transport transport;

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;

}

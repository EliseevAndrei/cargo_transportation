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
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name="order_user")
public class OrderUser extends AbstractEntity {

    private Double weight;
    private String phone;
    @Column(name="first_name")
    private String firstName;
    @Column(name="second_name")
    private String secondName;
    private String email;

    @ManyToOne
    @JoinColumn(name="start_point_id")
    private Point startPoint;

    @ManyToOne
    @JoinColumn(name="end_point_id")
    private Point endPoint;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;

}

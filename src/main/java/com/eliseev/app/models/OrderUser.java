package com.eliseev.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name="order_user")
public class OrderUser extends AbstractEntity {

    @Column(name="first_name")
    private String firstName;
    @Column(name="second_name")
    private String secondName;
    private String email;
    private Long distance;

    private Double cost;
    private Double coefficient;

    @ManyToOne
    @JoinColumn(name="start_map_id")
    private Map startMap;

    @ManyToOne
    @JoinColumn(name="end_map_id")
    private Map endMap;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "orderUser", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderUserProduct> orderUserProducts = new ArrayList<>();

}

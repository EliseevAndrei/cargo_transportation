package com.eliseev.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Table(name="order_user_product")
public class OrderUserProduct extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name="order_user_id")
    private OrderUser orderUser;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private Double weight;

}

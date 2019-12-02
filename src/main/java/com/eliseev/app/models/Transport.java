package com.eliseev.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Transport extends AbstractEntity {

    @NotBlank(message = "Название транспорта обязательно")
    private String name;
    private Integer speed;
    @Column(name="max_weight")
    private Double maxWeight;
    @Column(name="cost_per_km")
    private BigDecimal costPerKm;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Map> maps = new ArrayList<>();

    public Transport(Long id, @NotBlank(message = "Название транспорта обязательно") String name, Integer speed, Double maxWeight, BigDecimal costPerKm) {
        super(id);
        this.name = name;
        this.speed = speed;
        this.maxWeight = maxWeight;
        this.costPerKm = costPerKm;
        this.maps = maps;
    }
}

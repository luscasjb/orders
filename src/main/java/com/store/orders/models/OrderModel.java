package com.store.orders.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.store.orders.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel extends RepresentationModel<OrderModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idOrder")
    @Column(name = "CD_PEDIDO")
    private Long idOrder;

    @Column(name = "VR_PEDIDO", nullable = false)
    @JsonProperty("valueOrder")
    private BigDecimal vrOrder;

    @Enumerated(EnumType.STRING)
    @JsonProperty("status")
    @Column(name = "CD_STATUS", nullable = false)
    private StatusOrder statusOrder;

    @Column(name = "DT_CRIACAO")
    @JsonProperty("createdAt")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;

    @Column(name = "DT_ATUALIZACAO")
    @JsonProperty("updateAt")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime updateAt;

}

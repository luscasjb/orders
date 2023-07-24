package com.store.orders.repositories;

import com.store.orders.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    /*
    @Query(
            select p from Produto p
            where
            p.preco >= :preco
            and
            p.dataCadastro >= data
            )
    List<Produto> buscarPorPrecoEData(BigDecimal preco, LocalDate data);
    @Query("""
            select orderModel from OrderModel orderModel
            where
            orderModel.status != ABANDONED
            """)
    OrderModel getOrdersByStatus(StatusOrder statusOrder, LocalDateTime dateAtt);*/
}

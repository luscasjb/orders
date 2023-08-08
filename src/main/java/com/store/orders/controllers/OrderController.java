package com.store.orders.controllers;

import com.store.orders.dtos.OrderDTO;
import com.store.orders.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final RabbitTemplate rabbitTemplate;

    @GetMapping
    public Page<OrderDTO> getAllOrders(@PageableDefault(page = 0, size = 5) Pageable pageable){
        return orderService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOneOrder(@PathVariable(value = "id") Long id){
        OrderDTO orderDto = orderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody @Valid OrderDTO orderDto, UriComponentsBuilder uriComponentsBuilder){
        OrderDTO dto = orderService.saveOrder(orderDto);
        URI address = uriComponentsBuilder.path("/order/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid OrderDTO orderDto){
        OrderDTO updated = orderService.updateOrder(id, orderDto);
        logger.info("Atualização de pedido bem sucedida!");
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDTO> deleteOrder(@PathVariable(value = "id") Long id){
        orderService.deleteOrder(id);
        logger.info("Pedido deletado!");
        return ResponseEntity.noContent().build();
    }

}

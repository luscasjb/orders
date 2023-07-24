package com.store.orders.controllers;

import com.store.orders.dtos.OrderRecordDto;
import com.store.orders.models.OrderModel;
import com.store.orders.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderService orderService;

    @PostMapping
    @Transactional
    public ResponseEntity<OrderModel> saveProduct(@RequestBody @Valid OrderRecordDto orderRecordDto){
        var orderModel = new OrderModel();
        /*Converte o Dto para Model*/
        BeanUtils.copyProperties(orderRecordDto, orderModel);
        /*Retorna o status created(201) e no corpo o que foi salvo */
        logger.info("Dados salvos com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(orderModel));
    }

    /*sem hateoas
    @GetMapping("/order")
    public ResponseEntity<List<OrderModel>> getAllOrder(){
        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.findAll());
    }*/
    @GetMapping
    public ResponseEntity<Page<OrderModel>> getAllOrders(@PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<OrderModel> orderPage = orderService.findAll(pageable);
        logger.info("Busca de todos pedidos bem sucedida!");
        return ResponseEntity.status(HttpStatus.OK).body(orderPage);
    }

    /*sem hateoas
    @GetMapping("/order/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable(value = "id") Long id){
        Optional<OrderModel> orderO = orderRepository.findById(id);
        if(orderO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderO.get());
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable(value = "id") Long id){
        Optional<OrderModel> orderO = orderService.findById(id);
        if(orderO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        logger.info("Busca de pedido por id bem sucedida!");
        return ResponseEntity.status(HttpStatus.OK).body(orderO.get());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> updateOrder(@PathVariable(value = "id") Long id,
                                              @RequestBody @Valid OrderRecordDto orderRecordDto){
        Optional<OrderModel> orderO = orderService.findById(id);
        if(orderO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        var orderModel = orderO.get();
        BeanUtils.copyProperties(orderRecordDto, orderModel);
        logger.info("Atualização de pedido bem sucedida!");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.save(orderModel));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id){
        Optional<OrderModel> orderO = orderService.findById(id);
        if(orderO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        orderService.delete(orderO.get());
        logger.info("Pedido deletado!");
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted successfully.");
    }

}

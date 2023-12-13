package com.faisal.repositories;

import com.faisal.entities.Order;
import com.faisal.entities.OrderProduct;
import com.faisal.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Integer> {
    OrderProduct findByOrderAndProduct(Order order, Product product);
}

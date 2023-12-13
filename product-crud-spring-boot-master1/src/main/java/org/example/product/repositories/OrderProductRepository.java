package org.example.product.repositories;
import org.example.product.entities.Order;
import org.example.product.entities.OrderProduct;
import org.example.product.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Integer> {
    OrderProduct findByOrderAndProduct(Order order, Product product);
}

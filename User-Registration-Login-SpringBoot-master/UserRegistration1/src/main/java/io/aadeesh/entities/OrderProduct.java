package com.faisal.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_product")
public class OrderProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    //private int orderId;
    //private int productId;

   // public OrderProduct() {}

    public Order getOrder() {return order;}
    public void setOrder(Order order) {this.order = order;}

    public int getOrderId() {return order.getId();}
    //public void setOrderId(Order order) {this.orderId = order.getId();}

    public int getProductId() {return product.getId();}
    //public void setProductId(Product product) {this.productId = product.getId();}

    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}

    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}
}
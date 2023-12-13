package com.faisal.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String productId;
    private String name;
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderproduct;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getProductId() {return productId;}

    public void setProductId(String productId) {this.productId = productId;}

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}

    public Set<OrderProduct> getOrderProduct() {return orderproduct;}

    public void setOrderProduct(Set<OrderProduct> orderproduct) {this.orderproduct = orderproduct;}
}

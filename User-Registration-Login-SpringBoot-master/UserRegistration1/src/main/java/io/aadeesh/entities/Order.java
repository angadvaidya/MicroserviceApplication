package com.faisal.entities;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String customerName;
    private String requiredDate;
    private String shippedDate;
    private String shipVia;
    private String shipAddress;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    //@OneToMany(mappedBy = "order")
    public List<OrderProduct> getOrderProducts() {return orderProducts;}
    public void setOrderProducts(List<OrderProduct> orderProducts) {this.orderProducts = orderProducts;}

    public String getCustomerName() {return customerName;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}

    public String getRequiredDate() {return requiredDate;}
    public void setRequiredDate(String requiredDate) {this.requiredDate = requiredDate;}

    public String getShippedDate() {return shippedDate;}
    public void setShippedDate(String shippedDate) {this.shippedDate = shippedDate;}

    public String getShipVia() {return shipVia;}
    public void setShipVia(String shipVia) {this.shipVia = shipVia;}

    public String getShipAddress() {return shipAddress;}
    public void setShipAddress(String shipAddress) {this.shipAddress = shipAddress;}
}

package org.example.product.controllers;

import org.example.product.entities.Order;
import org.example.product.entities.OrderProduct;
import org.example.product.entities.Product;
import org.example.product.repositories.OrderProductRepository;
import org.example.product.repositories.OrderRepository;
import org.example.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;


    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    //all orders view
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String index(Model model){
        List<Order> orders = (List<Order>) orderRepository.findAll();
        System.out.println("***********************8"+orders);
        model.addAttribute("orders", orders);
        return "orders";
    }

    //single order view
    @RequestMapping("order/{id}")
    public String showOrder(@PathVariable Integer id, Model model){
        model.addAttribute("order", orderRepository.findOne(id));
        return "ordershow";
    }

    //create order
    @RequestMapping("order/new")
    public String newOrder(Model model){
        model.addAttribute("order", new Order());
        return "ordersaveform";
    }

    //save new order
    @RequestMapping(value = "order/save", method = RequestMethod.POST)
    public String saveOrder(Order order, WebRequest webRequest){
        String productId =  webRequest.getParameter("productid");
        String quantity =  webRequest.getParameter("quantity");
        String [] products = productId.split(",");
        String [] quantities = quantity.split(",");
        System.out.println(products);
        orderRepository.save(order);
        for (int i = 0; i<products.length; i++) {
            OrderProduct orderProduct = new OrderProduct();
            Product product = productRepository.findOne(Integer.parseInt(products[i]));
            orderProduct.setOrder(order);
            orderProduct.setQuantity(Integer.parseInt(quantities[i]));
            orderProduct.setProduct(product);
            orderProductRepository.save(orderProduct);
        }
        return "redirect:/order/" + order.getId();
    }

    //edit order
    @RequestMapping("order/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("order", orderRepository.findOne(id));
        /* List<OrderProduct> orderProducts = new ArrayList<>();
        model.addAttribute("orderproduct", orderProducts);*/
        return "ordereditform";
    }

    //update order
    @RequestMapping(value = "order/update", method = RequestMethod.POST)
    public String updateOrder(Order order, WebRequest webRequest){
        orderRepository.save(order);
        String productId =  webRequest.getParameter("products");
        String quantity =  webRequest.getParameter("quantitities");
        String productOldId =  webRequest.getParameter("productsold");
        //String orderproductid =  webRequest.getParameter("orderproductid");


        String [] products = productId.split(",");
        String [] quantities = quantity.split(",");
        String [] productOldIds = productOldId.split(",");
        //String [] orderproductids = orderproductid.split(",");

        System.out.println("----OLD PRODUCTS---"+ Arrays.toString(products));
        System.out.println("----NEW PRODUCT---"+Arrays.toString(productOldIds));

        //delete old product
        for (int i = 0; i<productOldIds.length; i++) {
            Product product = productRepository.findOne(Integer.parseInt(productOldIds[i]));
            OrderProduct oldorderProduct = orderProductRepository.findByOrderAndProduct(order, product);
            orderProductRepository.delete(oldorderProduct);
        }

        for (int i = 0; i<products.length; i++) {
            Product product = productRepository.findOne(Integer.parseInt(products[i]));
            //OrderProduct orderProduct1 =  orderProductRepository.findByOrderAndProduct(order,product);
            //orderProductRepository.delete(orderProduct1);
            //System.out.println("!@#$%^^&***************!@!#!#"+orderProduct1);

            OrderProduct neworderProduct = new OrderProduct();
            neworderProduct.setOrder(order);
            neworderProduct.setQuantity(Integer.parseInt(quantities[i]));
            neworderProduct.setProduct(product);
            orderProductRepository.save(neworderProduct);

            if(!Arrays.equals(products,productOldIds) ){
                System.out.println("1");
            }
        }
        //orderProductRepository.save();
        return "redirect:/order/" + order.getId();
    }

    //delete order
    @RequestMapping("order/delete/{id}")
    public String delete(@PathVariable Integer id){
        orderRepository.delete(id);
        return "redirect:/orders";
    }
}

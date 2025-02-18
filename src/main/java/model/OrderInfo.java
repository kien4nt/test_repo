/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author anhkc
 */
public class OrderInfo {
    private String orderID;
    private Date orderDate;
    private String deliveryAddress;
    private String paymentMethodID;
    private String deliveryOptionID;
    private double orderTotalAmount;
    private String customerID;
    private int paymentStatusID;
    private int deliveryStatusID;
    private List<OrderItem> itemList;

    public OrderInfo() {
    }
    
    
    /**
     *  BILL constructor
     * @param orderID
     * @param orderDate
     * @param orderTotalAmount
     * @param paymentStatusID
     * @param deliveryStatusID 
     */
    public OrderInfo(String orderID, Date orderDate, double orderTotalAmount, int paymentStatusID, int deliveryStatusID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderTotalAmount = orderTotalAmount;
        this.paymentStatusID = paymentStatusID;
        this.deliveryStatusID = deliveryStatusID;
    }

    public OrderInfo(String orderID, Date orderDate, String deliveryAddress, String paymentMethodID, String deliveryOptionID, double orderTotalAmount, String customerID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethodID = paymentMethodID;
        this.deliveryOptionID = deliveryOptionID;
        this.orderTotalAmount = orderTotalAmount;
        this.customerID = customerID;
    }
    
    
    
    public OrderInfo(String orderID, Date orderDate, String customerID, List<OrderItem> itemList) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.itemList = itemList;
    }

    public OrderInfo(String orderID, Date orderDate, double orderTotalAmount, String customerID, List<OrderItem> itemList) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderTotalAmount = orderTotalAmount;
        this.customerID = customerID;
        this.itemList = itemList;
    }
    
    

    public OrderInfo(String orderID, Date orderDate, String deliveryAddress, String paymentMethodID, String deliveryOptionID, double orderTotalAmount, String customerID, int paymentStatusID, int deliveryStatusID, List<OrderItem> itemList) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethodID = paymentMethodID;
        this.deliveryOptionID = deliveryOptionID;
        this.orderTotalAmount = orderTotalAmount;
        this.customerID = customerID;
        this.paymentStatusID = paymentStatusID;
        this.deliveryStatusID = deliveryStatusID;
        this.itemList = itemList;
    }

    public OrderInfo(String orderID, Date orderDate, String deliveryAddress, String paymentMethodID, String deliveryOptionID, double orderTotalAmount, String customerID, int paymentStatusID, int deliveryStatusID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethodID = paymentMethodID;
        this.deliveryOptionID = deliveryOptionID;
        this.orderTotalAmount = orderTotalAmount;
        this.customerID = customerID;
        this.paymentStatusID = paymentStatusID;
        this.deliveryStatusID = deliveryStatusID;
    }


    public int getPaymentStatusID() {
        return paymentStatusID;
    }

    public void setPaymentStatusID(int paymentStatusID) {
        this.paymentStatusID = paymentStatusID;
    }

    public int getDeliveryStatusID() {
        return deliveryStatusID;
    }

    public void setDeliveryStatusID(int deliveryStatusID) {
        this.deliveryStatusID = deliveryStatusID;
    }
    
    

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(String paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public String getDeliveryOptionID() {
        return deliveryOptionID;
    }

    public void setDeliveryOptionID(String deliveryOptionID) {
        this.deliveryOptionID = deliveryOptionID;
    }

    public double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }


    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    
    
    
    
}

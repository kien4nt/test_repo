/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Acer
 */
public class Bill {

    private String orderID;
    private int paymentStatusID;
    private int deliveryStatusID;
    private double billTotalAmount;
    private Date billDate;

    

    public Bill(String orderID, int paymentStatusID, int deliveryStatusID, double billTotalAmount, Date billDate) {
        this.orderID = orderID;
        this.paymentStatusID = paymentStatusID;
        this.deliveryStatusID = deliveryStatusID;
        this.billTotalAmount = billTotalAmount;
        this.billDate = billDate;
    }

  

    public String getOrderID() {
        return orderID;
    }

    public int getPaymentStatusID() {
        return paymentStatusID;
    }

    public int getDeliveryStatusID() {
        return deliveryStatusID;
    }

    public double getBillTotalAmount() {
        return billTotalAmount;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setPaymentStatusID(int paymentStatusID) {
        this.paymentStatusID = paymentStatusID;
    }

    public void setDeliveryStatusID(int deliveryStatusID) {
        this.deliveryStatusID = deliveryStatusID;
    }

    public void setBillTotalAmount(double billTotalAmount) {
        this.billTotalAmount = billTotalAmount;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
    
}

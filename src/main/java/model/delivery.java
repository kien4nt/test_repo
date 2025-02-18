/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TRUNG NHAN
 */
public class delivery {

    private String deliveryOptionID;
    private String optionName;
    private int estimatedTime;
    private double optionCost;

    public delivery(String deliveryOptionID, String optionName, int estimatedTime, double optionCost) {
        this.deliveryOptionID = deliveryOptionID;
        this.optionName = optionName;
        this.estimatedTime = estimatedTime;
        this.optionCost = optionCost;
    }

    public String getDeliveryOptionID() {
        return deliveryOptionID;
    }

    public void setDeliveryOptionID(String deliveryOptionID) {
        this.deliveryOptionID = deliveryOptionID;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public double getOptionCost() {
        return optionCost;
    }

    public void setOptionCost(double optionCost) {
        this.optionCost = optionCost;
    }
   

  

}

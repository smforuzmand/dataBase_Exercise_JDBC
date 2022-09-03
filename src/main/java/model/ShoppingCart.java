package model;

import java.time.LocalDateTime;

public class ShoppingCart {
    private int id;
    private LocalDateTime lastUpdate;
    private String orderStatus;
    private String deliveryAddress;
    private String customerReference;
    private boolean paymentApproved;

    public ShoppingCart(int id, LocalDateTime lastUpdate, String deliveryAddress, String customerService, boolean paymentApproved , String orderStatus) {
        this.id = id;
        this.lastUpdate = lastUpdate;
        this.deliveryAddress = deliveryAddress;
        this.customerReference = customerService;
        this.paymentApproved = paymentApproved;
        this.orderStatus = orderStatus;
    }

    public ShoppingCart() {
    }

    public ShoppingCart(int id, LocalDateTime last_update, String order_status, String delivery_address, String customer_reference) {

    }


    public int getId() {
        return id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public boolean isPaymentApproved() {
        return paymentApproved;
    }

    public void setPaymentApproved(boolean paymentApproved) {
        this.paymentApproved = paymentApproved;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", lastUpdate=" + lastUpdate +
                ", DeliveryAddress='" + deliveryAddress + '\'' +
                ", customerService='" + customerReference + '\'' +
                ", paymentApproved=" + paymentApproved +
                '}';
    }
}

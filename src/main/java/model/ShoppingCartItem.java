package model;

import java.util.Optional;

public class ShoppingCartItem {
    private int id;
    private int amount;
    private double totalPrice;
    private Product item;
    private ShoppingCart card;

    public ShoppingCartItem(int id, int amount, double totalPrice, Product item, ShoppingCart card) {
        this.id = id;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.item = item;
        this.card = card;
    }

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(int id, int amount, double total_price, Optional<Product> product_id, Optional<ShoppingCart> shopping_cart_id) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public ShoppingCart getCard() {
        return card;
    }

    public void setCard(ShoppingCart card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "id=" + id +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", item=" + item +
                ", card=" + card +
                '}';
    }
}


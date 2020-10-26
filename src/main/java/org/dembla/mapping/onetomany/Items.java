package org.dembla.mapping.onetomany;

import javax.persistence.*;

@Entity
@Table(name="Items")
public class Items {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="item_id")
    private String itemId;

    @Column(name="item_total")
    private double itemTotal;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;

    public Items( String itemId, double itemTotal, int quantity,Cart cart) {
        this.id = id;
        this.itemId = itemId;
        this.itemTotal = itemTotal;
        this.quantity = quantity;
        this.cart = cart ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

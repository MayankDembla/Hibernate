package org.dembla.mapping.onetomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cart_id")
    private long id;

    @Column(name="total")
    private double total;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="cart")
    private Set<Items> items;

    public Cart( String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Items> getItems() {
        return items;
    }

    public void setItems(Set<Items> items) {
        this.items = items;
    }
}

package org.dembla.mapping.manytomany;

import org.dembla.mapping.onetomany.Items;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CartNew")
public class CartNew {

    @Id
    @Column(name ="cart_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id ;

    @Column(name = "cart_total")
    private double total ;

    @ManyToMany(targetEntity = Item1.class, cascade = {CascadeType.ALL} )
    @JoinTable(name = "CART_ITEMS" ,
            joinColumns = { @JoinColumn(name="cart_id")},
            inverseJoinColumns = {@JoinColumn(name="item_id")})
    private Set<Item1> item1s ;

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

    public Set<Item1> getItem1s() {
        return item1s;
    }

    public void setItem1s(Set<Item1> item1s) {
        this.item1s = item1s;
    }
}

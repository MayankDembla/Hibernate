package org.dembla.mapping.onetoone;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TRANSACTION")
public class Txn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="txn_id")
    private long id ;

    @Column(name="txn_date")
    private Date date ;

    @Column(name="txn_total")
    private double total ;

    @OneToOne(mappedBy="txn")
    @Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Customer customer ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Txn{" +
                "id=" + id +
                ", date=" + date +
                ", total=" + total +
                ", customer=" + customer +
                '}';
    }
}

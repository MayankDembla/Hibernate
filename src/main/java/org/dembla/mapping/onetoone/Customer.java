package org.dembla.mapping.onetoone;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name= " CUSTOMER")
public class Customer {

    // need @GenericGenerator so that id is used from the txn rather than generating it.
    @Id
    @Column(name="txn_id",unique = true,nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name="gen",
            strategy = "foreign",
             parameters = {@org.hibernate.annotations.Parameter(name="property", value = "txn")})
    private long id;

    @Column(name="cust_name")
    private String name;

    @Column(name="cust_email")
    private String email;

    @Column(name="cust_address")
    private String address;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Txn txn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Txn getTxn() {
        return txn;
    }

    public void setTxn(Txn txn) {
        this.txn = txn;
    }
}

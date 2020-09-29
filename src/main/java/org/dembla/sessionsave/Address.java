package org.dembla.sessionsave;

import org.dembla.model.Employee;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@Access(value = AccessType.FIELD)
public class Address {

    @Id
    @Column(name="emp_id" , unique = true, nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name="gen", strategy = "foreign",
            parameters = {@org.hibernate.annotations.Parameter(name = "property", value = "employee")})
    private long id ;

    @Column(name = "ZipCode")
    private String zipCode  ;

    @Column(name = "city")
    private String city ;

    @OneToOne
    @PrimaryKeyJoinColumn  // This annotation specifies a primary key column that is used as a foreign key to join to another table.
    private Emp employee ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Emp getEmployee() {
        return employee;
    }

    public void setEmployee(Emp employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

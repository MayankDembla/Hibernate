package org.dembla.sessionsave;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EMP")
@Access(value = AccessType.FIELD) // By default the access type is defined by the place where you put your identifier annotation (@Id).
                                  // If you put it on the field - it will be AccessType.FIELD,
                                  //  if you put it on the getter - it will be AccessType.PROPERTY.
public class Emp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private long id ;

    @Column(name="emp_name")
    private  String name ;

    @Column(name = "emp_salary")
    private double salary ;

    @OneToOne(mappedBy = "employee")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Address address ;


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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return id == emp.id &&
                Double.compare(emp.salary, salary) == 0 &&
                Objects.equals(name, emp.name) &&
                Objects.equals(address, emp.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, address);
    }
}

package demo.domain;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the CUSTOMERS database table.
 */
@Entity
@Table(name = "CUSTOMERS")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
@ToString(exclude = "sales")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int customerid;
    private String country;
    private int debt;
    private String name;
    private int salary;
    private List<Sale> sales;

    public Customer() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    public int getCustomerid() {
        return this.customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }


    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public int getDebt() {
        return this.debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    //bi-directional many-to-one association to Sale
    @OneToMany(mappedBy = "customer")
    public List<Sale> getSales() {
        return this.sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public Sale addSale(Sale sale) {
        getSales().add(sale);
        sale.setCustomer(this);

        return sale;
    }

    public Sale removeSale(Sale sale) {
        getSales().remove(sale);
        sale.setCustomer(null);

        return sale;
    }

}
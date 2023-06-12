package dev.dex.fixerprodockerpostgres.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_order")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_and_surname")
    private String nameAndSurname;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "delivery_method")
    private String deliveryMethod;

    @Column(name = "address")
    private String address;

    @Column(name = "comment")
    private String comment;

    @Column(name = "total")
    private double total;

    @Column(name = "products")
    private String products;

    public CustomerOrder() {
    }

    public CustomerOrder(String nameAndSurname, String telephone, String deliveryMethod, String address, String comment, double total, String products) {
        this.nameAndSurname = nameAndSurname;
        this.telephone = telephone;
        this.deliveryMethod = deliveryMethod;
        this.address = address;
        this.comment = comment;
        this.total = total;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", nameAndSurname='" + nameAndSurname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", address='" + address + '\'' +
                ", comment='" + comment + '\'' +
                ", total=" + total +
                ", products='" + products + '\'' +
                '}';
    }
}

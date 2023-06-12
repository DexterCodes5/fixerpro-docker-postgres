package dev.dex.fixerprodockerpostgres.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "filter")
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "brand")
    private String brand;

    @Column(name = "number")
    private String number;

    @Column(name = "compatible_cars")
    private String compatibleCars;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "price")
    private String price;

    public Filter() {
    }

    public Filter(String type, String brand, String number, String compatibleCars, String thumbnail, String price) {
        this.type = type;
        this.brand = brand;
        this.number = number;
        this.compatibleCars = compatibleCars;
        this.thumbnail = thumbnail;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCompatibleCars() {
        return compatibleCars;
    }

    public void setCompatibleCars(String compatibleCars) {
        this.compatibleCars = compatibleCars;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", number='" + number + '\'' +
                ", compatibleCars='" + compatibleCars + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

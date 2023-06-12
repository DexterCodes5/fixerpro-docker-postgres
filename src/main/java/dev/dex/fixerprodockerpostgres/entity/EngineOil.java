package dev.dex.fixerprodockerpostgres.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "engine_oil")
public class EngineOil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "liters")
    private String liters;

    @Column(name = "price")
    private String price;

    public EngineOil() {
    }

    public EngineOil(String title, String thumbnail, String liters, String price) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.liters = liters;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLiters() {
        return liters;
    }

    public void setLiters(String liters) {
        this.liters = liters;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Oil{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", liters='" + liters + '\'' +
                ", price=" + price +
                '}';
    }
}
